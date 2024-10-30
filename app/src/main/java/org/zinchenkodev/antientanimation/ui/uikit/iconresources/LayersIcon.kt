package org.zinchenkodev.antientanimation.ui.uikit.iconresources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val LayersIcon: ImageVector
    get() {
        if (_LayersIc32 != null) {
            return _LayersIc32!!
        }
        _LayersIc32 = ImageVector.Builder(
            name = "LayersIc32",
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
                moveTo(28f, 23.39f)
                lineTo(15.987f, 28.75f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15.987f, 28.75f)
                lineTo(4f, 23.39f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(28f, 16.75f)
                lineTo(15.987f, 22.11f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15.987f, 22.11f)
                lineTo(4f, 16.75f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
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

        return _LayersIc32!!
    }

@Suppress("ObjectPropertyName")
private var _LayersIc32: ImageVector? = null
