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

val ColorPaletteIcon: ImageVector
    get() {
        if (_ColorPaletteIc32 != null) {
            return _ColorPaletteIc32!!
        }
        _ColorPaletteIc32 = ImageVector.Builder(
            name = "ColorPaletteIc32",
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
                moveTo(15.999f, 8.5f)
                curveTo(15.796f, 8.5f, 15.632f, 8.664f, 15.635f, 8.867f)
                curveTo(15.635f, 9.069f, 15.799f, 9.233f, 16.001f, 9.233f)
                curveTo(16.204f, 9.233f, 16.368f, 9.069f, 16.368f, 8.867f)
                curveTo(16.365f, 8.664f, 16.203f, 8.5f, 15.999f, 8.5f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9.232f, 15.999f)
                curveTo(9.232f, 15.796f, 9.068f, 15.632f, 8.867f, 15.635f)
                curveTo(8.664f, 15.635f, 8.5f, 15.799f, 8.5f, 16.001f)
                curveTo(8.5f, 16.204f, 8.664f, 16.368f, 8.867f, 16.368f)
                curveTo(9.069f, 16.368f, 9.232f, 16.203f, 9.232f, 15.999f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21.303f, 10.696f)
                curveTo(21.16f, 10.553f, 20.928f, 10.553f, 20.787f, 10.697f)
                curveTo(20.644f, 10.84f, 20.644f, 11.072f, 20.787f, 11.215f)
                curveTo(20.929f, 11.357f, 21.161f, 11.357f, 21.304f, 11.215f)
                curveTo(21.447f, 11.071f, 21.447f, 10.84f, 21.303f, 10.696f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(11.213f, 20.785f)
                curveTo(11.071f, 20.643f, 10.839f, 20.643f, 10.697f, 20.787f)
                curveTo(10.555f, 20.929f, 10.555f, 21.161f, 10.697f, 21.304f)
                curveTo(10.84f, 21.447f, 11.072f, 21.447f, 11.215f, 21.304f)
                curveTo(11.357f, 21.161f, 11.357f, 20.929f, 11.213f, 20.785f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(11.215f, 11.213f)
                curveTo(11.357f, 11.071f, 11.357f, 10.839f, 11.213f, 10.697f)
                curveTo(11.071f, 10.555f, 10.839f, 10.555f, 10.696f, 10.697f)
                curveTo(10.553f, 10.84f, 10.553f, 11.072f, 10.696f, 11.215f)
                curveTo(10.839f, 11.357f, 11.071f, 11.357f, 11.215f, 11.213f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(16f, 28f)
                curveTo(9.26f, 28f, 3.817f, 22.443f, 4.005f, 15.66f)
                curveTo(4.179f, 9.399f, 9.399f, 4.179f, 15.66f, 4.005f)
                curveTo(22.443f, 3.817f, 28f, 9.26f, 28f, 16f)
                verticalLineTo(17.333f)
                curveTo(28f, 18.807f, 26.807f, 20f, 25.333f, 20f)
                horizontalLineTo(22.583f)
                curveTo(20.811f, 20f, 19.532f, 21.696f, 20.019f, 23.399f)
                lineTo(20.361f, 24.6f)
                curveTo(20.849f, 26.304f, 19.569f, 28f, 17.799f, 28f)
                horizontalLineTo(16f)
                close()
            }
        }.build()

        return _ColorPaletteIc32!!
    }

@Suppress("ObjectPropertyName")
private var _ColorPaletteIc32: ImageVector? = null

