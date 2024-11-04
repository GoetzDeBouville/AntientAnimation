package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import org.zinchenkodev.antientanimation.R
import org.zinchenkodev.antientanimation.presentation.models.Event
import org.zinchenkodev.antientanimation.presentation.models.State
import org.zinchenkodev.antientanimation.presentation.models.Tool

@Suppress("t")
@Composable
fun Body(
    modifier: Modifier = Modifier,
    state: State = State(),
    onAction: (Event) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .pointerInput(state.selectedTool) {
                detectDragGestures(
                    onDragStart = {},
                    onDragEnd = {
                        if (state.isPlaying.not()) {
                            onAction(Event.OnDragEnd)
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        when (state.selectedTool) {
                            is Tool.Pen -> if (state.isPlaying.not()) {
                                onAction(
                                    Event.OnDrawLine(
                                        change.position - dragAmount,
                                        change.position,
                                        state.strokeColor
                                    )
                                )
                            }

                            is Tool.Eraser -> if (state.isPlaying.not()) {
                                onAction(
                                    Event.OnEraseLine(
                                        change.position - dragAmount,
                                        change.position
                                    )
                                )
                            }
                        }
                    }
                )
            }
            .pointerInput(state.selectedTool) {
                detectTapGestures { offset ->
                    when (state.selectedTool) {
                        is Tool.Pen -> if (state.isPlaying.not()) {
                            onAction(
                                Event.OnDrawPoint(
                                    start = offset.copy(
                                        x = offset.x - (state.strokeWidth / 2).toPx(),
                                        y = offset.y
                                    ),
                                    end = offset.copy(
                                        x = offset.x + (state.strokeWidth / 2).toPx(),
                                        y = offset.y
                                    ),
                                    state.strokeColor
                                )
                            )
                        }

                        is Tool.Eraser -> if (state.isPlaying.not()) {
                            onAction(Event.OnEraseLine(offset, offset))
                        }
                    }
                }
            },
        shape = RoundedCornerShape(20.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .paint(
                    painter = painterResource(R.drawable.background_img),
                    contentScale = ContentScale.Crop
                ),
        ) {
            state.lineList.forEach { line ->
                drawLine(
                    color = line.color,
                    start = line.startDrawing.toOffset(),
                    end = line.endDrawing.toOffset(),
                    strokeWidth = state.strokeWidth.toPx()
                )
            }

            if (state.previousFrame.isNotEmpty() && state.isPlaying.not()) {
                state.previousFrame.forEach { line ->
                    drawLine(
                        color = line.color.copy(0.2f),
                        start = line.startDrawing.toOffset(),
                        end = line.endDrawing.toOffset(),
                        strokeWidth = state.strokeWidth.toPx()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBody() {
    Body()
}
