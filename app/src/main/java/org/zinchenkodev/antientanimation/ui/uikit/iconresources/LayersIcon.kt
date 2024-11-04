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
import org.zinchenkodev.antientanimation.ui.theme.Gray

@Composable
fun LayersIcon(isActive: Boolean): ImageVector {
    return remember(isActive) {
        val color = if (isActive) {
            Color(0xFFFFFFFF)
        } else {
            Gray
        }

        ImageVector.Builder(
            name = "LayersIc32",
            defaultWidth = 32.dp,
            defaultHeight = 33.dp,
            viewportWidth = 32f,
            viewportHeight = 33f
        ).apply {
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(28f, 23.39f)
                lineTo(15.987f, 28.75f)
            }
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15.987f, 28.75f)
                lineTo(4f, 23.39f)
            }
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(28f, 16.75f)
                lineTo(15.987f, 22.11f)
            }
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15.987f, 22.11f)
                lineTo(4f, 16.75f)
            }
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(4f, 10.115f)
                lineTo(15.983f, 15.482f)
                lineTo(28f, 10.115f)
                lineTo(16.017f, 4.75f)
                lineTo(4f, 10.115f)
                close()
            }
        }.build()
    }
}