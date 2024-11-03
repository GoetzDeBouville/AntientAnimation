package org.zinchenkodev.antientanimation.presentation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.toOffset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.zinchenkodev.antientanimation.presentation.models.Event
import org.zinchenkodev.antientanimation.domain.models.Line
import org.zinchenkodev.antientanimation.presentation.models.State
import org.zinchenkodev.antientanimation.presentation.models.Tool
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
                isPlaying = false
            )
        }
    }

    @Suppress("t")
    fun accept(event: Event) {
        when (event) {
            is Event.OnDrawLine -> onDrawLine(event)
            is Event.OnEraseLine -> onEraseLine(event)
            is Event.OnBackIconClicked -> onBackIconClicked()
            is Event.OnForwardIconClicked -> onForwardIconClicked()
            is Event.OnToolClick -> onToolClick(event)
            is Event.OnEraserClicked -> onEraserClicked()
            is Event.OnPenClicked -> onPenClicked()
            is Event.OnColorPickerClicked -> onColorPickerClicked()
            is Event.OnClearFrameClicked -> onClearFrameClicked()
            is Event.OnColorChanged -> {
                _state.update { currentState ->
                    currentState.copy(
                        strokeColor = event.color,
                        selectedTool = Tool.Pen(event.color)
                    )
                }
            }

            is Event.OnCreateNewFrameClicked -> onCreateNewFrameClicked()

            is Event.OnPlayClicked -> onPlayClicked()

            is Event.OnPauseClicked -> {
                _state.update {
                    it.copy(
                        isPlaying = false,
                        lineList = if (it.backActionBackStack.size > 0) {
                            it.backActionBackStack.last
                        } else {
                            emptyList()
                        }
                    )
                }
            }
        }
    }

    private fun onDrawLine(event: Event.OnDrawLine) {
        viewModelScope.launch(Dispatchers.Default) {
            _state.update { currentState ->
                val currentLines = currentState.lineList.toMutableList()
                currentLines.add(
                    Line(
                        event.start.toIntOffset(),
                        event.end.toIntOffset(),
                        event.color
                    )
                )

                if (currentState.backActionBackStack.size > MAX_HISTORY_SIZE) {
                    currentState.copy(
                        lineList = currentLines,
                        backActionBackStack = currentState.backActionBackStack.apply {
                            removeFirst()
                            addLast(currentLines)
                        },
                        forwardActionBackStack = ArrayDeque()
                    )
                } else {
                    currentState.copy(
                        lineList = currentLines,
                        backActionBackStack = currentState.backActionBackStack.apply {
                            addLast(
                                currentLines
                            )
                        },
                        forwardActionBackStack = ArrayDeque()
                    )
                }
            }
        }
    }

    private fun onEraseLine(event: Event.OnEraseLine) {
        viewModelScope.launch(Dispatchers.Default) {
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
                if (currentState.backActionBackStack.size > 0 && newLineList != currentState.backActionBackStack.last) {
                    currentState.backActionBackStack.addLast(newLineList)
                    if (currentState.backActionBackStack.size > MAX_HISTORY_SIZE) {
                        currentState.copy(
                            lineList = newLineList,
                            backActionBackStack = currentState.backActionBackStack.apply {
                                removeFirst()
                                addLast(newLineList)
                            },
                            forwardActionBackStack = ArrayDeque()
                        )
                    } else {
                        currentState.copy(
                            lineList = newLineList,
                            backActionBackStack = currentState.backActionBackStack.apply {
                                addLast(
                                    newLineList
                                )
                            },
                            forwardActionBackStack = ArrayDeque()
                        )
                    }
                } else {
                    currentState.copy(
                        lineList = newLineList,
                        backActionBackStack = currentState.backActionBackStack,
                        forwardActionBackStack = ArrayDeque()
                    )
                }
            }
        }
    }

    private fun onBackIconClicked() {
        _state.update { currentState ->
            if (currentState.backActionBackStack.size > 1) {
                val lastItemFrame = currentState.backActionBackStack.peekLast()
                val updatedLines = currentState.backActionBackStack.removeLast()
                currentState.copy(
                    lineList = updatedLines,
                    backActionBackStack = currentState.backActionBackStack,
                    forwardActionBackStack = currentState.forwardActionBackStack.apply {
                        addLast(lastItemFrame)
                    }
                )
            } else {
                val lastItemFrame = currentState.backActionBackStack.peekLast()
                val updatedLines = currentState.backActionBackStack.removeLast()
                currentState.copy(
                    lineList = updatedLines,
                    backActionBackStack = ArrayDeque(),
                    forwardActionBackStack = currentState.forwardActionBackStack.apply {
                        addLast(lastItemFrame)
                    }
                )
            }
        }
    }

    private fun onForwardIconClicked() {
        _state.update { currentState ->
            val lastAction = currentState.forwardActionBackStack.removeLast()
            currentState.backActionBackStack.addLast(lastAction)
            val updatedLines = currentState.backActionBackStack.peekLast() ?: emptyList()
            currentState.copy(
                lineList = updatedLines,
                backActionBackStack = currentState.backActionBackStack,
                forwardActionBackStack = currentState.forwardActionBackStack
            )
        }
    }

    private fun onToolClick(event: Event.OnToolClick) {
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

    private fun onEraserClicked() {
        _state.update { currentState ->
            currentState.copy(
                selectedTool = Tool.Eraser
            )
        }
    }

    private fun onPenClicked() {
        _state.update { currentState ->
            currentState.copy(
                selectedTool = Tool.Pen()
            )
        }
    }

    private fun onColorPickerClicked() {
        _state.update { currentState ->
            val isActive = currentState.colorPickerIsActive
            currentState.copy(
                colorPickerIsActive = isActive.not()
            )
        }
    }

    private fun onClearFrameClicked() {
        _state.update { currentState ->
            if (currentState.backActionBackStack.isEmpty()) {
                currentState.copy(
                    lineList = emptyList(),
                    forwardActionBackStack = ArrayDeque()
                )
            } else if (currentState.backActionBackStack.last.isEmpty()) {
                currentState.copy(
                    lineList = emptyList(),
                    forwardActionBackStack = ArrayDeque()
                )
            } else {
                currentState.backActionBackStack.addLast(emptyList())
                currentState.copy(
                    lineList = emptyList(),
                    backActionBackStack = currentState.backActionBackStack,
                    forwardActionBackStack = ArrayDeque()
                )
            }
        }
    }

    private fun onCreateNewFrameClicked() {
        _state.update { currentState ->
            val frameList = currentState.frameList.toMutableList()
            frameList.add(currentState.lineList)

            currentState.copy(
                backActionBackStack = ArrayDeque(),
                forwardActionBackStack = ArrayDeque(),
                lineList = emptyList(),
                frameList = frameList,
            )
        }
    }

    private fun onPlayClicked() {
        _state.update {
            it.copy(
                isPlaying = true
            )
        }

        viewModelScope.launch {
            while (_state.value.isPlaying) {
                for (frame in _state.value.frameList) {
                    if (_state.value.isPlaying.not()) {
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

    private companion object {
        const val ERASER_RADIUS_10 = 10.0f
        const val MAX_HISTORY_SIZE = 128
    }
}

fun Offset.toIntOffset(): IntOffset = IntOffset(this.x.toInt(), this.y.toInt())

fun Offset.distanceTo(other: Offset): Float {
    val dx = other.x - this.x
    val dy = other.y - this.y
    return sqrt(dx * dx + dy * dy)
}
