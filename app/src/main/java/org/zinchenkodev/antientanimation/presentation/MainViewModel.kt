package org.zinchenkodev.antientanimation.presentation

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.toOffset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.zinchenkodev.antientanimation.models.Event
import org.zinchenkodev.antientanimation.models.Line
import org.zinchenkodev.antientanimation.models.Point
import org.zinchenkodev.antientanimation.models.State
import org.zinchenkodev.antientanimation.models.Tool
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.sqrt

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state
        get() = _state.asStateFlow()

    init {

    }

    @Suppress("t")
    fun accept(event: Event) {
        when (event) {
            is Event.OnDrawLine -> {
                _state.update { currentState ->
                    val currentLines = currentState.lineList.toMutableList()
                    currentLines.add(
                        Line(
                            event.start.toIntOffset(),
                            event.end.toIntOffset(),
                            event.color
                        )
                    )
                    Log.i("TAG", "OnDrawLine event.color = ${event.color}")

                    currentState.copy(lineList = currentLines)
                }
            }

            is Event.OnDrawPoint -> {
                _state.update { currentState ->
                    val pointerList = currentState.pointerList.toMutableList()
                    pointerList.add(Point(event.point.toIntOffset(), event.color))
                    currentState.copy(pointerList = pointerList)
                }
            }

            is Event.OnEraseLine -> {
                _state.update { currentState ->
                    val newLineList = currentState.lineList.filterNot { line ->
                        val lineStart = line.startDrawing.toOffset()
                        val lineEnd = line.endDrawing.toOffset()
                        val removePoints =
                            getPoints(event.start.toIntOffset(), event.end.toIntOffset())

                        removePoints.any { point ->
                            distanceFromPointToLineSegment(
                                point.toOffset(),
                                lineStart,
                                lineEnd
                            ) <= ERASER_RADIUS_10
                        }
                    }

                    val newPointerList = currentState.pointerList.filter { point ->
                        val removePoints =
                            getPoints(event.start.toIntOffset(), event.end.toIntOffset())

                        removePoints.all { erasePoint ->
                            distance(erasePoint, point.offset) > ERASER_RADIUS_10
                        }
                    }

                    currentState.copy(
                        lineList = newLineList,
                        pointerList = newPointerList
                    )
                }
            }

            is Event.OnErasePoint -> {
                _state.update { currentState ->
                    val erasePoint = event.point.toIntOffset()
                    val erasePointers = mutableListOf<IntOffset>()

                    val pointerList = currentState.pointerList.filter { point ->
                        val isNotErasing = distance(erasePoint, point.offset) > ERASER_RADIUS_10
                        if (!isNotErasing) {
                            erasePointers.add(point.offset)
                        }
                        isNotErasing
                    }

                    if (erasePointers.isNotEmpty()) {
                        currentState.copy(
                            backAction = currentState.backAction + Pair(Tool.Pen(), erasePointers),
                            nextAction = emptyList(),
                            pointerList = pointerList,
                        )
                    } else {
                        currentState
                    }
                }
            }

            is Event.OnToolClick -> {
                when (event.tool) {
                    is Tool.Pen -> _state.update {
                        _state.value.copy(selectedTool = Tool.Pen())
                    }

                    is Tool.Eraser -> {
                        _state.update { currentState ->
                            val lines = currentState.lineList
                            val points = mutableListOf<Point>()
                            for (line in lines) {
                                val pointers = getPoints(line.startDrawing, line.endDrawing)
                                for (point in pointers) {
                                    points.add(Point(point, line.color))
                                }
                            }

                            currentState.copy(
                                lineList = emptyList(),
                                pointerList = points + currentState.pointerList,
                                selectedTool = event.tool,
                            )
                        }
                    }
                }
            }

            is Event.OnDragEnd -> {
                _state.update { currentState ->
                    if (currentState.selectedTool == Tool.Pen()) {
                        val lines = currentState.lineList
                        val points: List<Point> = getLinePoints(lines)

                        currentState.copy(
                            lineList = emptyList(),
                            pointerList = points + currentState.pointerList
                        )
                    } else {
                        if (currentState.erasePointers.isNotEmpty()) {
                            currentState.copy(
                                erasePointers = emptyList()
                            )
                        } else {
                            currentState
                        }
                    }
                }
            }

            is Event.OnBackIconClicked -> {
            }

            is Event.OnForwardIconClicked -> {
            }

            is Event.OnEraserClicked -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedTool = Tool.Eraser
                    )
                }
            }

            is Event.OnPenClicked -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedTool = Tool.Pen()
                    )
                }
            }

            is Event.OnColorPickerClicked -> {
                _state.update { currentState ->
                    val isActive = currentState.colorPickerIsActive
                    currentState.copy(
                        colorPickerIsActive = isActive.not()
                    )
                }
            }

            is Event.OnClearFrameClicked -> {
                _state.update {
                    it.copy(
                        lineList = emptyList(),
                        pointerList = emptyList(),
                        erasePointers = emptyList()
                    )
                }
            }

            is Event.OnColorChanged -> {
                _state.update { currentState ->
                    Log.i(
                        "TAG",
                        "OnColorChanged Updating color from ${currentState.strokeColor} to ${event.color}"
                    )
                    currentState.copy(
                        strokeColor = event.color
                    )
                }

                Log.i("TAG", "OnColorChanged state.color = ${_state.value.strokeColor}")
                Log.i("TAG", "OnColorChanged state.value = ${state.value}")

            }

            is Event.OnCreateNewFrameClicked -> {
                _state.update { currentState ->
                    val frameList = currentState.frameList.toMutableList()
                    frameList.add(Pair(currentState.pointerList, currentState.lineList))

                    currentState.copy(
                        backAction = emptyList(),
                        nextAction = emptyList(),
                        pointerList = emptyList(),
                        lineList = emptyList(),
                        frameList = frameList,
                        frameNumber = frameList.size + 1
                    )
                }
            }

            is Event.OnPlayClicked -> {
                _state.update {
                    it.copy(
                        onPlay = true
                    )
                }

                viewModelScope.launch {
                    while (_state.value.onPlay) {
                        for (frame in _state.value.frameList) {
                            if (_state.value.onPlay.not()) {
                                break
                            }
                            _state.update {
                                it.copy(
                                    pointerList = frame.first,
                                    lineList = frame.second
                                )
                            }
                            delay(1000)
                        }
                    }
                }
            }

            is Event.OnPauseClicked -> {
                _state.update {
                    it.copy(
                        onPlay = false
                    )
                }
            }
        }
    }

    private fun doHistoryAction(
        actionList: List<Pair<Tool, List<IntOffset>>>,
        updateList: List<Pair<Tool, List<IntOffset>>>,
        statePointers: List<IntOffset>
    ): Triple<List<Pair<Tool, List<IntOffset>>>, List<Pair<Tool, List<IntOffset>>>, List<IntOffset>> {

        val mActionList = actionList.toMutableList()
        val mUpdateList = updateList.toMutableList()

        val nextAction = mActionList.last()
        mActionList.removeAt(mActionList.size - 1)

        val actionTool = nextAction.first
        val actionPointers = nextAction.second

        val pointers = if (actionTool == Tool.Eraser) {
            mUpdateList.add(Pair(Tool.Pen(), actionPointers))
            statePointers.removePointers(actionPointers)
        } else {
            mUpdateList.add(Pair(Tool.Eraser, actionPointers))
            statePointers.addPointers(actionPointers)
        }

        return Triple(
            mActionList,
            mUpdateList,
            pointers
        )
    }

    private fun List<IntOffset>.removePointers(
        erasePointers: List<IntOffset>
    ): List<IntOffset> {
        val array = this.toMutableList()

        for (point in erasePointers) {
            if (point in array) {
                array.remove(point)
            }
        }

        return array
    }

    private fun List<IntOffset>.addPointers(
        penPointers: List<IntOffset>,
    ): List<IntOffset> {
        val array = this.toMutableList()
        for (point in penPointers) {
            array.add(point)
        }

        return array
    }

    private fun getLinePoints(lines: List<Line>): List<Point> {
        val points = mutableListOf<Point>()
        for (line in lines) {
            val pointers = getPoints(line.startDrawing, line.endDrawing)
            for (offset in pointers) {
                points.add(Point(offset, line.color))
            }
        }

        return points
    }

    private fun distanceFromPointToLineSegment(
        point: Offset,
        lineStart: Offset,
        lineEnd: Offset
    ): Float {
        val lineLength = lineStart.distanceTo(lineEnd)
        if (lineLength == 0f) {
            return point.distanceTo(lineStart)
        }

        val t = ((point.x - lineStart.x) *
                (lineEnd.x - lineStart.x) +
                (point.y - lineStart.y) *
                (lineEnd.y - lineStart.y)) /
                (lineLength * lineLength)

        val tClamped = t.coerceIn(0f, 1f)
        val nearestPoint = Offset(
            lineStart.x + tClamped * (lineEnd.x - lineStart.x),
            lineStart.y + tClamped * (lineEnd.y - lineStart.y)
        )

        return point.distanceTo(nearestPoint)
    }


    private fun getPoints(start: IntOffset, end: IntOffset): List<IntOffset> {
        val points = mutableListOf<IntOffset>()

        val dx = end.x - start.x
        val dy = end.y - start.y

        val steps = maxOf(abs(dx), abs(dy))

        val xStep = dx.toFloat() / steps
        val yStep = dy.toFloat() / steps

        var x = start.x.toFloat()
        var y = start.y.toFloat()

        for (i in 0..steps) {
            points.add(IntOffset(x.toInt(), y.toInt()))
            x += xStep
            y += yStep
        }

        return points
    }

    private fun distance(point1: IntOffset, point2: IntOffset): Float {
        val x = (point1.x - point2.x).toFloat()
        val y = (point1.y - point2.y).toFloat()

        return sqrt(x * x + y * y)
    }

    override fun onCleared() {
        super.onCleared()
        _state.update {
            it.copy(
                onPlay = false
            )
        }
    }

    companion object {
        private const val ERASER_RADIUS_10 = 10.0f
    }
}

fun Offset.toIntOffset(): IntOffset = IntOffset(this.x.toInt(), this.y.toInt())

fun Offset.distanceTo(other: Offset): Float {
    val dx = other.x - this.x
    val dy = other.y - this.y
    return sqrt(dx * dx + dy * dy)
}
