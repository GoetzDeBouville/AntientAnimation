package org.zinchenkodev.antientanimation.models

import androidx.compose.ui.geometry.Offset

sealed interface Event {
    class OnToolClick(val tool: Tool) : Event
    class OnDrawLine(val start: Offset, val end: Offset) : Event
    data object OnDragEnd : Event
    class OnDrawPoint(val point: Offset) : Event
    class OnEraseLine(val start: Offset, val end: Offset) : Event
    class OnErasePoint(val point: Offset) : Event
}