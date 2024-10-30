package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.zinchenkodev.antientanimation.R

@Composable
fun Body(
    modifier: Modifier = Modifier
) {
    val background = painterResource(R.drawable.background_img)
    val path = remember { Path() }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Image(
            painter = background,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .shadow(elevation = 8.dp)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            path.moveTo(offset.x, offset.y)
                        }, onDrag = { change, _ ->
                            change.consume()
                            path.lineTo(change.position.x, change.position.y)
                        }
                    )
                }
        ) {
            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(width = 5f)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBody() {
    Body()
}
