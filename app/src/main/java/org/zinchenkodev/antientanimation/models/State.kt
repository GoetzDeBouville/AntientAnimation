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
    val backActionBackStack: Deque<List<Line>> = ArrayDeque(), // contains list of frames from history
    val forwardActionBackStack: Deque<List<Line>> = ArrayDeque(), // contains list of frames from history
    val colorPickerIsActive: Boolean = false,
    val isPlaying: Boolean = false
)
