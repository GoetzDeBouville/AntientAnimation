package org.zinchenkodev.antientanimation.presentation

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
import java.util.ArrayDeque
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.sqrt

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state
        get() = _state.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        _state.update {
            it.copy(
                onPlay = false
            )
        }
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
                    currentState.backActionList.addLast(currentLines)
                    currentState.copy(
                        lineList = currentLines,
                        backActionList = currentState.backActionList,
                        forwardActionList = ArrayDeque()
                    )
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
                    currentState.backActionList.addLast(newLineList)

                    currentState.copy(
                        lineList = newLineList,
                        backActionList = currentState.backActionList,
                        forwardActionList = ArrayDeque()
                    )
                }
            }

            is Event.OnDragEnd -> {
                _state.update { currentState ->
                    if (currentState.selectedTool == Tool.Pen()) {
                        currentState.copy(
                            lineList = emptyList(),
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
                _state.update { currentState ->
                    val lastAction = currentState.backActionList.removeLast()
                    currentState.forwardActionList.addLast(lastAction)
                    val updatedLines = currentState.backActionList.peekLast() ?: emptyList()
                    currentState.copy(
                        lineList = updatedLines,
                        backActionList = currentState.backActionList,
                        forwardActionList = currentState.forwardActionList
                    )
                }
            }

            is Event.OnForwardIconClicked -> {
                _state.update { currentState ->
                    val lastAction = currentState.forwardActionList.removeLast()
                    currentState.backActionList.addLast(lastAction)
                    val updatedLines = currentState.backActionList.peekLast() ?: emptyList()
                    currentState.copy(
                        lineList = updatedLines,
                        backActionList = currentState.backActionList,
                        forwardActionList = currentState.forwardActionList
                    )
                }
            }

            is Event.OnToolClick -> {
                when (event.tool) {
                    is Tool.Pen -> _state.update {
                        _state.value.copy(selectedTool = Tool.Pen())
                    }

                    is Tool.Eraser -> {
                        _state.update { currentState ->
                            currentState.copy(
                                selectedTool = event.tool,
                            )
                        }
                    }
                }
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
                        erasePointers = emptyList()
                    )
                }
            }

            is Event.OnColorChanged -> {
                _state.update { currentState ->
                    currentState.copy(
                        strokeColor = event.color,
                        selectedTool = Tool.Pen(event.color)
                    )
                }
            }

            is Event.OnCreateNewFrameClicked -> {
                _state.update { currentState ->
                    val frameList = currentState.frameList.toMutableList()
                    frameList.add(currentState.lineList)

                    currentState.copy(
                        backActionList = ArrayDeque(),
                        forwardActionList = ArrayDeque(),
                        lineList = emptyList(),
                        frameList = frameList,
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
                                    lineList = frame
                                )
                            }
                            delay(500)
                        }
                    }
                }
            }

            is Event.OnPauseClicked -> {
                _state.update {
                    it.copy(
                        onPlay = false,
                        lineList = emptyList()
                    )
                }
            }
        }
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

    private fun getLinePoints(lines: List<Line>): List<Point> {
        val points = mutableListOf<Point>()
        for (line in lines) {
            val pointers = getPoints(line.startDrawing, line.endDrawing)
            for (point in pointers) {
                points.add(Point(point, line.color))
            }
        }

        return points
    }

    private fun distance(point1: IntOffset, point2: IntOffset): Float {
        val x = (point1.x - point2.x).toFloat()
        val y = (point1.y - point2.y).toFloat()

        return sqrt(x * x + y * y)
    }

    private companion object {
        const val ERASER_RADIUS_10 = 10.0f
    }
}

fun Offset.toIntOffset(): IntOffset = IntOffset(this.x.toInt(), this.y.toInt())

fun Offset.distanceTo(other: Offset): Float {
    val dx = other.x - this.x
    val dy = other.y - this.y
    return sqrt(dx * dx + dy * dy)
}
