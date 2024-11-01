package org.zinchenkodev.antientanimation.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

typealias frame = Pair<List<Point>, List<Line>>

data class State(
    val frameNumber: Int = 0,
    val strokeWidth: Dp = 4.dp,
    val strokeColor: Color = Color.Blue,
    val lineList: List<Line> = emptyList(),
    val frameList: List<frame> = emptyList(),
    val pointerList: List<Point> = emptyList(),
    val selectedTool: Tool = Tool.Pen(),
    val erasePointers: List<IntOffset> = emptyList(),
    val linesNumber: Int = lineList.size,
    val currentLineNumber: Int = linesNumber,
    val backAction: List<Pair<Tool, List<IntOffset>>> = emptyList(),
    val nextAction: List<Pair<Tool, List<IntOffset>>> = emptyList(),
    val colorPickerIsActive: Boolean = false
)