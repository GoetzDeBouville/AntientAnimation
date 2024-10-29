package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PauseIc32: ImageVector
    get() {
        if (_PauseIc32 != null) {
            return _PauseIc32!!
        }
        _PauseIc32 = ImageVector.Builder(
            name = "PauseIc32",
            defaultWidth = 33.dp,
            defaultHeight = 33.dp,
            viewportWidth = 33f,
            viewportHeight = 33f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(13f, 4.75f)
                horizontalLineTo(11.5f)
                curveTo(11.086f, 4.75f, 10.75f, 5.518f, 10.75f, 6.464f)
                verticalLineTo(27.036f)
                curveTo(10.75f, 27.983f, 11.086f, 28.75f, 11.5f, 28.75f)
                horizontalLineTo(13f)
                curveTo(13.414f, 28.75f, 13.75f, 27.983f, 13.75f, 27.036f)
                verticalLineTo(6.464f)
                curveTo(13.75f, 5.518f, 13.414f, 4.75f, 13f, 4.75f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(21f, 4.75f)
                horizontalLineTo(19.5f)
                curveTo(19.086f, 4.75f, 18.75f, 5.518f, 18.75f, 6.464f)
                verticalLineTo(27.036f)
                curveTo(18.75f, 27.983f, 19.086f, 28.75f, 19.5f, 28.75f)
                horizontalLineTo(21f)
                curveTo(21.414f, 28.75f, 21.75f, 27.983f, 21.75f, 27.036f)
                verticalLineTo(6.464f)
                curveTo(21.75f, 5.518f, 21.414f, 4.75f, 21f, 4.75f)
                close()
            }
        }.build()

        return _PauseIc32!!
    }

@Suppress("ObjectPropertyName")
private var _PauseIc32: ImageVector? = null
