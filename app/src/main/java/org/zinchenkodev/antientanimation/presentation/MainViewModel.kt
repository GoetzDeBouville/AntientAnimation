package org.zinchenkodev.antientanimation.presentation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.toOffset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.zinchenkodev.antientanimation.domain.ClearDbAndResetTableUseCase
import org.zinchenkodev.antientanimation.domain.GetFrameListUseCase
import org.zinchenkodev.antientanimation.domain.GetFramesCountUseCase
import org.zinchenkodev.antientanimation.domain.SaveFrameUseCase
import org.zinchenkodev.antientanimation.domain.models.Line
import org.zinchenkodev.antientanimation.presentation.models.Event
import org.zinchenkodev.antientanimation.presentation.models.State
import org.zinchenkodev.antientanimation.presentation.models.Tool
import java.util.ArrayDeque
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.sqrt

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveFrameUseCase: SaveFrameUseCase,
    private val getFrameListUseCase: GetFrameListUseCase,
    private val getFramesCountUseCase: GetFramesCountUseCase,
    private val clearDbAndResetTableUseCase: ClearDbAndResetTableUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state
        get() = _state.asStateFlow()

    /**
     * Clear db and reset table for testing
     */
    init {
        viewModelScope.launch {
            clearDbAndResetTableUseCase()
        }
    }

    override fun onCleared() {
        super.onCleared()
        _state.update {
            it.copy(
                isPlaying = false
            )
        }
    }

    fun accept(event: Event) {
        when (event) {
            is Event.OnDrawLine -> onDrawLine(event)
            is Event.OnDrawPoint -> onDrawPoint(event)
            is Event.OnEraseLine -> onEraseLine(event)
            is Event.OnDragEnd -> {
                if (_state.value.isPlaying.not()) {
                    _state.update {
                        it.copy(
                            backActionBackStack = it.backActionBackStack.apply {
                                addLast(it.lineList)
                            },
                            forwardActionBackStack = ArrayDeque()
                        )
                    }
                }
            }

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

    private fun onDrawPoint(event: Event.OnDrawPoint) {
        if (_state.value.isPlaying.not()) {
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
    }

    private fun onDrawLine(event: Event.OnDrawLine) {
        if (_state.value.isPlaying.not()) {
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
                            lineList = currentLines
                        )
                    } else {
                        currentState.copy(
                            lineList = currentLines
                        )
                    }
                }
            }
        }
    }

    private fun onEraseLine(event: Event.OnEraseLine) {
        if (_state.value.isPlaying.not()) {
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
                                lineList = newLineList
                            )
                        } else {
                            currentState.copy(
                                lineList = newLineList
                            )
                        }
                    } else {
                        currentState.copy(
                            lineList = newLineList
                        )
                    }
                }
            }
        }
    }

    private fun onBackIconClicked() {
        _state.update { currentState ->
            if (currentState.backActionBackStack.isEmpty()) {
                currentState
            } else {
                val lastItemFrame = currentState.backActionBackStack.removeLast()

                if (currentState.backActionBackStack.isEmpty()) {
                    currentState.copy(
                        lineList = emptyList(),
                        backActionBackStack = ArrayDeque(),
                        forwardActionBackStack = currentState.forwardActionBackStack.apply {
                            addLast(lastItemFrame)
                        }
                    )
                } else {
                    currentState.copy(
                        lineList = currentState.backActionBackStack.peekLast(),
                        backActionBackStack = currentState.backActionBackStack,
                        forwardActionBackStack = currentState.forwardActionBackStack.apply {
                            addLast(lastItemFrame)
                        }
                    )
                }
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
            viewModelScope.launch(Dispatchers.IO) {
                saveFrameUseCase(frame = currentState.lineList)
            }

            currentState.copy(
                backActionBackStack = ArrayDeque(),
                forwardActionBackStack = ArrayDeque(),
                previousFrame = currentState.lineList,
                lineList = emptyList(),
                framesNumber = currentState.framesNumber + 1
            )
        }
    }

    @Suppress("t")
    private fun onPlayClicked() {
        _state.update {
            it.copy(
                isPlaying = true
            )
        }

        viewModelScope.launch {
            val framesCount = getFramesCountUseCase()
            var startIndex = 0L
            val frameListFirst = mutableListOf<List<Line>>()
            val frameListSecond = mutableListOf<List<Line>>()
            frameListFirst.addAll(getFrameListUseCase(startIndex = startIndex))
            startIndex += frameListFirst.size - 1

            if (startIndex == framesCount - 1) {
                while (_state.value.isPlaying) {
                    for (frame in frameListFirst) {
                        if (!_state.value.isPlaying) break

                        _state.update { it.copy(lineList = frame) }
                        delay(200)
                    }
                }
            } else {
                while (_state.value.isPlaying) {
                    for (i in 0 until frameListFirst.size) {
                        if (!_state.value.isPlaying) break

                        _state.update { it.copy(lineList = frameListFirst[i]) }
                        delay(200)

                        if (i + PAGGING_CALLBACK_SIZE == frameListFirst.size) {
                            async(Dispatchers.IO) {
                                frameListSecond.addAll(getFrameListUseCase(startIndex = startIndex))
                                startIndex =
                                    if (startIndex + frameListSecond.size <= framesCount - 1) {
                                        startIndex + frameListSecond.size - 1
                                    } else {
                                        0L
                                    }
                            }
                        }
                    }

                    frameListFirst.clear()
                    for (i in 0 until frameListSecond.size) {
                        if (!_state.value.isPlaying) break

                        _state.update { it.copy(lineList = frameListSecond[i]) }
                        delay(200)

                        if (i + PAGGING_CALLBACK_SIZE == frameListSecond.size) {
                            async(Dispatchers.IO) {
                                frameListFirst.addAll(getFrameListUseCase(startIndex = startIndex))
                                startIndex =
                                    if (startIndex + frameListFirst.size <= framesCount - 1) {
                                        startIndex + frameListFirst.size - 1
                                    } else {
                                        0L
                                    }
                            }
                        }
                    }
                    frameListSecond.clear()
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
        const val MAX_HISTORY_SIZE = 256
        const val PAGGING_CALLBACK_SIZE = 1024
    }
}

fun Offset.toIntOffset(): IntOffset = IntOffset(this.x.toInt(), this.y.toInt())

fun Offset.distanceTo(other: Offset): Float {
    val dx = other.x - this.x
    val dy = other.y - this.y
    return sqrt(dx * dx + dy * dy)
}
