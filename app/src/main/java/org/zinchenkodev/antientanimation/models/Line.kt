package org.zinchenkodev.antientanimation.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset

data class Line(
    val startDrawing: IntOffset,
    val endDrawing: IntOffset,
    val color: Color
)