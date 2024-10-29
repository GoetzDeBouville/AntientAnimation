package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val ColorPickerIcon: ImageVector
    @Composable
    get() {
        var isChecked by remember { mutableStateOf(false) }

        _ColorPickerIc32 = remember(isChecked) {
            val onСheckedColor = if (isChecked) {
                Color(0xFFA8DB10)
            } else {
                Color.Transparent
            }
            ImageVector.Builder(
                name = "ColorPickerIc32",
                defaultWidth = 33.dp,
                defaultHeight = 33.dp,
                viewportWidth = 33f,
                viewportHeight = 33f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF1976D2)),
                    stroke = SolidColor(onСheckedColor),
                    strokeLineWidth = 1.5f
                ) {
                    moveTo(16.326f, 15.686f)
                    moveToRelative(-13.25f, 0f)
                    arcToRelative(13.25f, 13.25f, 0f, isMoreThanHalf = true, isPositiveArc = true, 26.5f, 0f)
                    arcToRelative(13.25f, 13.25f, 0f, isMoreThanHalf = true, isPositiveArc = true, -26.5f, 0f)
                }
            }.build()
        }

        return _ColorPickerIc32!!
    }

@Suppress("ObjectPropertyName")
private var _ColorPickerIc32: ImageVector? = null

@Preview
@Composable
private fun ShowImg() {
    Image(
        imageVector = ColorPickerIcon,
        contentDescription = null,
    )
}