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
fun InstrumentsIcon(isActive: Boolean): ImageVector {
    return remember(isActive) {
        val color = if (isActive) {
            Color(0xFFFFFFFF)
        } else {
            Gray
        }

        ImageVector.Builder(
            name = "InstrumentsIc32",
            defaultWidth = 33.dp,
            defaultHeight = 32.dp,
            viewportWidth = 33f,
            viewportHeight = 32f
        ).apply {
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(25.611f, 16.06f)
                verticalLineTo(6.727f)
                curveTo(25.611f, 5.253f, 24.418f, 4.06f, 22.945f, 4.06f)
                horizontalLineTo(6.945f)
                curveTo(5.471f, 4.06f, 4.278f, 5.253f, 4.278f, 6.727f)
                verticalLineTo(22.727f)
                curveTo(4.278f, 24.2f, 5.471f, 25.393f, 6.945f, 25.393f)
                horizontalLineTo(16.278f)
            }
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(27.786f, 25.683f)
                lineTo(25.901f, 27.568f)
                curveTo(25.379f, 28.09f, 24.535f, 28.09f, 24.015f, 27.568f)
                lineTo(13.974f, 17.527f)
                curveTo(13.723f, 17.278f, 13.583f, 16.938f, 13.583f, 16.584f)
                verticalLineTo(13.366f)
                horizontalLineTo(16.802f)
                curveTo(17.155f, 13.366f, 17.495f, 13.505f, 17.745f, 13.756f)
                lineTo(27.786f, 23.798f)
                curveTo(28.307f, 24.317f, 28.307f, 25.162f, 27.786f, 25.683f)
                verticalLineTo(25.683f)
                close()
            }
            path(
                stroke = SolidColor(color),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(25.397f, 21.413f)
                lineTo(21.623f, 25.173f)
            }
        }.build()
    }
}