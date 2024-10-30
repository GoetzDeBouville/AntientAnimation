package org.zinchenkodev.antientanimation.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

data class State(
    val frameNumber: Int = 0,
    val strokeWidth: Dp = 4.dp,
    val strokeColor: Color = Color.Blue,
    val lineList: List<Line> = emptyList(),
    val frameList: List<List<IntOffset>> = listOf(listOf()),
    val pointerList: List<IntOffset> = emptyList(),
    val selectedTool: Tool = Tool.Pen(),
    val erasePointers: List<IntOffset> = emptyList(),
)
