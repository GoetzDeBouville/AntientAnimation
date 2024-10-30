package org.zinchenkodev.antientanimation.ui.uikit.iconresources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PlayIcon: ImageVector
    get() {
        if (_PlayIc32 != null) {
            return _PlayIc32!!
        }
        _PlayIc32 = ImageVector.Builder(
            name = "PlayIc32",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(7.024f, 6.152f)
                verticalLineTo(25.843f)
                curveTo(7.026f, 26.247f, 7.14f, 26.642f, 7.354f, 26.984f)
                curveTo(7.568f, 27.326f, 7.873f, 27.601f, 8.235f, 27.779f)
                curveTo(8.598f, 27.956f, 9.002f, 28.029f, 9.404f, 27.989f)
                curveTo(9.805f, 27.949f, 10.188f, 27.798f, 10.508f, 27.552f)
                lineTo(24.143f, 16.949f)
                curveTo(24.413f, 16.738f, 24.629f, 16.466f, 24.774f, 16.156f)
                curveTo(24.918f, 15.845f, 24.987f, 15.505f, 24.975f, 15.162f)
                curveTo(24.962f, 14.82f, 24.868f, 14.486f, 24.701f, 14.186f)
                curveTo(24.534f, 13.887f, 24.298f, 13.632f, 24.013f, 13.442f)
                lineTo(10.378f, 4.356f)
                curveTo(10.052f, 4.142f, 9.675f, 4.02f, 9.286f, 4.002f)
                curveTo(8.897f, 3.985f, 8.51f, 4.073f, 8.167f, 4.256f)
                curveTo(7.823f, 4.44f, 7.536f, 4.714f, 7.335f, 5.047f)
                curveTo(7.134f, 5.381f, 7.026f, 5.762f, 7.024f, 6.152f)
                close()
            }
        }.build()

        return _PlayIc32!!
    }

@Suppress("ObjectPropertyName")
private var _PlayIc32: ImageVector? = null

