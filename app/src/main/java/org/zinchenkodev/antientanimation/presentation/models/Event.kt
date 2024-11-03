package org.zinchenkodev.antientanimation.presentation.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

sealed interface Event {
    class OnToolClick(val tool: Tool) : Event
    class OnDrawLine(val start: Offset, val end: Offset, val color: Color) : Event
    class OnEraseLine(val start: Offset, val end: Offset) : Event
    data object OnBackIconClicked : Event
    data object OnForwardIconClicked : Event
    data object OnEraserClicked: Event
    data object OnPenClicked: Event
    data object OnColorPickerClicked: Event
    data object OnClearFrameClicked: Event
    class OnColorChanged(val color: Color): Event
    data object OnCreateNewFrameClicked : Event
    data object OnPlayClicked : Event
    data object OnPauseClicked : Event
}