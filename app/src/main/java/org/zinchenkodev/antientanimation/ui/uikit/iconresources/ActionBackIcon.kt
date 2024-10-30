package org.zinchenkodev.antientanimation.ui.uikit.iconresources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val ActionBackIcon: ImageVector
    get() {
        if (_ActionBackIcon != null) {
            return _ActionBackIcon!!
        }
        _ActionBackIcon = ImageVector.Builder(
            name = "ActionForwardIc24",
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
                moveTo(6.093f, 7.996f)
                curveTo(7.881f, 7.517f, 9.78f, 7.673f, 11.466f, 8.437f)
                curveTo(13.151f, 9.201f, 14.52f, 10.526f, 15.339f, 12.186f)
                curveTo(16.157f, 13.845f, 16.375f, 15.738f, 15.955f, 17.54f)
                curveTo(15.75f, 18.419f, 15.4f, 19.249f, 14.925f, 20f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(8.724f, 4.967f)
                lineTo(5.896f, 7.796f)
                lineTo(8.724f, 10.624f)
            }
        }.build()

        return _ActionBackIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ActionBackIcon: ImageVector? = null