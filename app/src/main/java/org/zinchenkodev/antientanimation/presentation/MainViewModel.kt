package org.zinchenkodev.antientanimation.presentation

import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.zinchenkodev.antientanimation.models.Event
import org.zinchenkodev.antientanimation.models.Line
import org.zinchenkodev.antientanimation.models.State
import org.zinchenkodev.antientanimation.models.Tool
import org.zinchenkodev.antientanimation.utils.toIntOffset
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.sqrt

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state
        get() = _state.asStateFlow()

    @Suppress("t")
    fun accept(event: Event) {
        when (event) {
            is Event.OnDrawLine -> {
                _state.update { currentState ->
                    val currentLines = currentState.lineList.toMutableList()
                    currentLines.add(
                        Line(
                            event.start.toIntOffset(),
                            event.end.toIntOffset()
                        )
                    )

                    currentState.copy(lineList = currentLines)
                }
            }

            is Event.OnDrawPoint -> {
                _state.update { currentState ->
                    val pointerList = currentState.pointerList.toMutableList()
                    pointerList.add(event.point.toIntOffset())
                    currentState.copy(pointerList = pointerList)
                }
            }

            is Event.OnEraseLine -> {
                val removePoints = getPoints(event.start.toIntOffset(), event.end.toIntOffset())
                _state.update { currentState ->
                    var pointerList = currentState.pointerList
                    for (point in removePoints) {
                        pointerList = pointerList.filter { distance(point, it) > ERASER_RADIUS_20 }
                    }
                    currentState.copy(pointerList = pointerList)
                }
            }

            is Event.OnErasePoint -> {
                _state.update { currentState ->
                    val erasePoint = event.point.toIntOffset()
                    val pointerList = currentState.pointerList.filter {
                        distance(erasePoint, it) > ERASER_RADIUS_20
                    }
                    currentState.copy(pointerList = pointerList)
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
                            val points = mutableListOf<IntOffset>()
                            for (line in lines) {
                                val pointers = getPoints(line.startDrawing, line.endDrawing)
                                for (point in pointers) {
                                    points.add(point)
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
                        val points: List<IntOffset> = getLinePoints(lines)

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
        }
    }

    private fun getLinePoints(lines: List<Line>): List<IntOffset> {
        val points = mutableListOf<IntOffset>()
        for (line in lines) {
            val pointers = getPoints(line.startDrawing, line.endDrawing)
            for (point in pointers) {
                points.add(point)
            }
        }

        return points
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

    companion object {
        private const val ERASER_RADIUS_20 = 20.0f
    }
}