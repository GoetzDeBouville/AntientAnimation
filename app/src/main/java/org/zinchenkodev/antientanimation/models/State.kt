package org.zinchenkodev.antientanimation.models

import androidx.compose.ui.unit.IntOffset

data class State(
    val frameNumber: Int = 0,
    val strokeWidth: Float = 20f,
    val currentLines: List<Line> = emptyList(),
    val frameList: List<List<IntOffset>> = listOf(listOf()),
    val pointerList: List<IntOffset> = emptyList(),
    val selectedTool: Tool = Tool.Pen()
)
