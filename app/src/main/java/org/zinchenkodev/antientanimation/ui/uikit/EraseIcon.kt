package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val EraseIc32: ImageVector
    get() {
        if (_EraseIc32 != null) {
            return _EraseIc32!!
        }
        _EraseIc32 = ImageVector.Builder(
            name = "EraseIc32",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(26.671f, 28.005f)
                horizontalLineTo(5.329f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(10.631f, 24.003f)
                horizontalLineTo(15.229f)
                lineTo(25.988f, 13.244f)
                curveTo(26.426f, 12.808f, 26.671f, 12.215f, 26.671f, 11.597f)
                curveTo(26.671f, 10.979f, 26.426f, 10.386f, 25.988f, 9.95f)
                lineTo(20.717f, 4.678f)
                curveTo(20.28f, 4.241f, 19.687f, 3.995f, 19.069f, 3.995f)
                curveTo(18.451f, 3.995f, 17.859f, 4.241f, 17.422f, 4.678f)
                lineTo(6.012f, 16.088f)
                curveTo(5.574f, 16.525f, 5.329f, 17.118f, 5.329f, 17.736f)
                curveTo(5.329f, 18.354f, 5.574f, 18.946f, 6.012f, 19.383f)
                lineTo(10.631f, 24.003f)
                close()
            }
        }.build()

        return _EraseIc32!!
    }

@Suppress("ObjectPropertyName")
private var _EraseIc32: ImageVector? = null
