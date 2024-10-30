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
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import org.zinchenkodev.antientanimation.R
import org.zinchenkodev.antientanimation.models.Event
import org.zinchenkodev.antientanimation.models.State
import org.zinchenkodev.antientanimation.models.Tool

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
                        onAction(Event.OnDragEnd)
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        when (state.selectedTool) {
                            is Tool.Pen -> onAction(
                                Event.OnDrawLine(
                                    change.position - dragAmount,
                                    change.position
                                )
                            )

                            is Tool.Eraser -> onAction(
                                Event.OnEraseLine(
                                    change.position - dragAmount,
                                    change.position
                                )
                            )
                        }
                    }
                )
            }
            .pointerInput(state.selectedTool) {
                detectTapGestures { offset ->
                    when (state.selectedTool) {
                        is Tool.Pen -> onAction(Event.OnDrawPoint(offset))
                        is Tool.Eraser -> onAction(Event.OnErasePoint(offset))
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
                    color = state.strokeColor,
                    start = line.startDrawing.toOffset(),
                    end = line.endDrawing.toOffset(),
                    strokeWidth = state.strokeWidth.toPx()
                )
            }

            state.pointerList.forEach { point ->
                drawCircle(
                    color = state.strokeColor,
                    radius = (state.strokeWidth).toPx(),
                    center = point.toOffset(),
                    style = Fill
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBody() {
    Body()
}
