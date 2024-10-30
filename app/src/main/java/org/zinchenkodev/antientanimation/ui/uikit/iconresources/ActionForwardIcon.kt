package org.zinchenkodev.antientanimation.ui.uikit.iconresources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ActionForwardIcon: ImageVector
    get() {
        if (_ActionForwardIcon != null) {
            return _ActionForwardIcon!!
        }
        _ActionForwardIcon = ImageVector.Builder(
            name = "ActionForwardIcon",
            defaultWidth = 25.dp,
            defaultHeight = 24.dp,
            viewportWidth = 25f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16.759f, 7.996f)
                curveTo(14.972f, 7.517f, 13.073f, 7.673f, 11.387f, 8.437f)
                curveTo(9.701f, 9.201f, 8.332f, 10.526f, 7.514f, 12.186f)
                curveTo(6.695f, 13.845f, 6.478f, 15.738f, 6.898f, 17.54f)
                curveTo(7.103f, 18.419f, 7.453f, 19.249f, 7.928f, 20f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(14.128f, 4.967f)
                lineTo(16.957f, 7.796f)
                lineTo(14.128f, 10.624f)
            }
        }.build()

        return _ActionForwardIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ActionForwardIcon: ImageVector? = null
