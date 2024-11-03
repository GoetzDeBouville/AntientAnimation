package org.zinchenkodev.antientanimation.presentation.models

import androidx.compose.ui.graphics.Color

sealed interface Tool {
    class Pen(val color: Color = Color.Blue) : Tool
    data object Eraser: Tool
}