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

val BinIcon: ImageVector
    get() {
        if (_BinIc32 != null) {
            return _BinIc32!!
        }
        _BinIc32 = ImageVector.Builder(
            name = "BinIc32",
            defaultWidth = 32.dp,
            defaultHeight = 33.dp,
            viewportWidth = 32f,
            viewportHeight = 33f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(24f, 9.457f)
                horizontalLineTo(25.333f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 14.71f)
                verticalLineTo(22.79f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16f, 13.163f)
                verticalLineTo(24.19f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(20f, 14.71f)
                verticalLineTo(22.79f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(21.06f, 28.046f)
                horizontalLineTo(10.94f)
                curveTo(9.316f, 28.046f, 8f, 26.73f, 8f, 25.106f)
                verticalLineTo(9.454f)
                horizontalLineTo(24f)
                verticalLineTo(25.106f)
                curveTo(24f, 26.73f, 22.684f, 28.046f, 21.06f, 28.046f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21.333f, 9.454f)
                lineTo(20.349f, 6.381f)
                curveTo(20.172f, 5.829f, 19.659f, 5.454f, 19.08f, 5.454f)
                horizontalLineTo(12.92f)
                curveTo(12.34f, 5.454f, 11.827f, 5.829f, 11.651f, 6.381f)
                lineTo(10.667f, 9.454f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(6.667f, 9.457f)
                horizontalLineTo(8f)
            }
        }.build()

        return _BinIc32!!
    }

@Suppress("ObjectPropertyName")
private var _BinIc32: ImageVector? = null
