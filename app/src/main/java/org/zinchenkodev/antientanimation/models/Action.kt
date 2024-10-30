package org.zinchenkodev.antientanimation.models

import androidx.compose.ui.geometry.Offset

sealed interface Action {
    class OnToolClick(val tool: Tool) : Action
    class OnDrawLine(val start: Offset, val end: Offset) : Action
    class OnDrawPoint(val point: Offset) : Action
    class OnEraseLine(val start: Offset, val end: Offset) : Action
    class OnErasePoint(val point: Offset) : Action
}