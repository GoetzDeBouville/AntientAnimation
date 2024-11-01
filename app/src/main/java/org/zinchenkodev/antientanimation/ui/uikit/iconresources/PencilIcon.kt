package org.zinchenkodev.antientanimation.ui.uikit.iconresources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.zinchenkodev.antientanimation.ui.theme.Salad

@Composable
fun PencilIcon(isChecked: Boolean): ImageVector {
    return remember(isChecked) {
        val color = if (isChecked) {
            Salad
        } else {
            Color(0xFFFFFFFF)
        }

        ImageVector.Builder(
            name = "PencilIc32",
            defaultWidth = 33.dp,
            defaultHeight = 32.dp,
            viewportWidth = 33f,
            viewportHeight = 32f
        ).apply {
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(13.889f, 23.847f)
                lineTo(27.596f, 10.14f)
                curveTo(28.637f, 9.099f, 28.637f, 7.411f, 27.596f, 6.369f)
                lineTo(26.1f, 4.872f)
                curveTo(25.059f, 3.831f, 23.371f, 3.831f, 22.329f, 4.872f)
                lineTo(8.621f, 18.58f)
                curveTo(8.427f, 18.775f, 8.264f, 18.999f, 8.137f, 19.243f)
                lineTo(4.631f, 26.037f)
                curveTo(4.036f, 27.191f, 5.272f, 28.428f, 6.425f, 27.835f)
                lineTo(13.224f, 24.333f)
                curveTo(13.471f, 24.205f, 13.695f, 24.043f, 13.889f, 23.847f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(8.38f, 18.92f)
                lineTo(13.553f, 24.094f)
            }
        }.build()
    }
}