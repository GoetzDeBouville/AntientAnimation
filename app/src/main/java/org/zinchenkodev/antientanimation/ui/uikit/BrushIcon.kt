package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val BrushIcon: ImageVector
    get() {
        if (_BrushIc32 != null) {
            return _BrushIc32!!
        }
        _BrushIc32 = ImageVector.Builder(
            name = "BrushIc32",
            defaultWidth = 33.dp,
            defaultHeight = 32.dp,
            viewportWidth = 33f,
            viewportHeight = 32f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(27.711f, 5.723f)
                verticalLineTo(5.723f)
                curveTo(26.667f, 4.679f, 24.994f, 4.624f, 23.883f, 5.595f)
                lineTo(13.167f, 14.971f)
                curveTo(12.006f, 15.987f, 11.947f, 17.772f, 13.038f, 18.863f)
                lineTo(14.57f, 20.395f)
                curveTo(15.661f, 21.485f, 17.447f, 21.425f, 18.462f, 20.265f)
                lineTo(27.838f, 9.549f)
                curveTo(28.81f, 8.44f, 28.754f, 6.767f, 27.711f, 5.723f)
                verticalLineTo(5.723f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15.243f, 13.16f)
                lineTo(20.283f, 18.2f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5.549f, 27.097f)
                horizontalLineTo(10.486f)
                curveTo(14.466f, 27.097f, 16.459f, 22.285f, 13.645f, 19.471f)
                verticalLineTo(19.471f)
                curveTo(11.575f, 17.401f, 8.061f, 18.163f, 7.034f, 20.903f)
                lineTo(5.005f, 26.313f)
                curveTo(4.863f, 26.693f, 5.143f, 27.097f, 5.549f, 27.097f)
                close()
            }
        }.build()

        return _BrushIc32!!
    }

@Suppress("ObjectPropertyName")
private var _BrushIc32: ImageVector? = null
