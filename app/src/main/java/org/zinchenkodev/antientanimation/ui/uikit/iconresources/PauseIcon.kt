package org.zinchenkodev.antientanimation.ui.uikit.iconresources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.zinchenkodev.antientanimation.ui.theme.Gray

@Composable
fun PauseIcon(isActive: Boolean): ImageVector {
    return remember(isActive) {
        val color = if (isActive) {
            Color(0xFFFFFFFF)
        } else {
            Gray
        }

        ImageVector.Builder(
            name = "PauseIc32",
            defaultWidth = 33.dp,
            defaultHeight = 33.dp,
            viewportWidth = 33f,
            viewportHeight = 33f
        ).apply {
            path(fill = SolidColor(color)) {
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
            path(fill = SolidColor(color)) {
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
    }
}