package org.zinchenkodev.antientanimation.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.ArrayDeque
import java.util.Deque

data class State(
    val strokeWidth: Dp = 4.dp,
    val strokeColor: Color = Color.Blue,
    val lineList: List<Line> = emptyList(),
    val frameList: List<List<Line>> = emptyList(),
    val selectedTool: Tool = Tool.Pen(),
    val erasePointers: List<Point> = emptyList(),
    val linesNumber: Int = lineList.size,
    val currentLineNumber: Int = linesNumber,
    val backActionList: Deque<List<Line>> = ArrayDeque(), // contains list of frames
    val forwardActionList: Deque<List<Line>> = ArrayDeque(), // contains list of frames
    val colorPickerIsActive: Boolean = false,
    val onPlay: Boolean = false
)
