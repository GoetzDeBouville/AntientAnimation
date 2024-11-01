package org.zinchenkodev.antientanimation.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

data class State(
    val strokeWidth: Dp = 4.dp,
    val strokeColor: Color = Color.Blue,
    val lineList: List<Line> = emptyList(),
    val frameList: List<List<Line>> = emptyList(),
    val selectedTool: Tool = Tool.Pen(),
    val erasePointers: List<IntOffset> = emptyList(),
    val linesNumber: Int = lineList.size,
    val currentLineNumber: Int = linesNumber,
    val backAction: List<Pair<Tool, List<Line>>> = emptyList(),
    val forwardAction: List<Pair<Tool, List<Line>>> = emptyList(),
    val colorPickerIsActive: Boolean = false,
    val onPlay: Boolean = false
)
