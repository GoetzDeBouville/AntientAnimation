package org.zinchenkodev.antientanimation.domain.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset

data class Point(
    val offset: IntOffset,
    val color: Color = Color.Blue
)
