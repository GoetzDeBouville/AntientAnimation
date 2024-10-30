package org.zinchenkodev.antientanimation.ui.uikit.iconresources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AddFileIcon: ImageVector
    get() {
        if (_AddFileIc32 != null) {
            return _AddFileIc32!!
        }
        _AddFileIc32 = ImageVector.Builder(
            name = "AddFileIc32",
            defaultWidth = 32.dp,
            defaultHeight = 33.dp,
            viewportWidth = 32f,
            viewportHeight = 33f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(24.552f, 9.302f)
                lineTo(20.781f, 5.531f)
                curveTo(20.281f, 5.031f, 19.603f, 4.75f, 18.896f, 4.75f)
                horizontalLineTo(9.333f)
                curveTo(7.86f, 4.75f, 6.667f, 5.943f, 6.667f, 7.417f)
                verticalLineTo(26.083f)
                curveTo(6.667f, 27.557f, 7.86f, 28.75f, 9.333f, 28.75f)
                horizontalLineTo(22.667f)
                curveTo(24.14f, 28.75f, 25.333f, 27.557f, 25.333f, 26.083f)
                verticalLineTo(11.187f)
                curveTo(25.333f, 10.481f, 25.052f, 9.802f, 24.552f, 9.302f)
                verticalLineTo(9.302f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(25.333f, 11.417f)
                horizontalLineTo(20f)
                curveTo(19.264f, 11.417f, 18.667f, 10.819f, 18.667f, 10.083f)
                verticalLineTo(4.75f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16f, 15.528f)
                verticalLineTo(22.35f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(19.411f, 18.938f)
                horizontalLineTo(12.589f)
            }
        }.build()

        return _AddFileIc32!!
    }

@Suppress("ObjectPropertyName")
private var _AddFileIc32: ImageVector? = null
