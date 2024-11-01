package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import org.zinchenkodev.antientanimation.models.Event
import org.zinchenkodev.antientanimation.models.State
import org.zinchenkodev.antientanimation.models.Tool
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.BrushIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.ColorPickerIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.EraseIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.InstrumentsIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PencilIcon


@Composable
fun Footer(
    modifier: Modifier = Modifier,
    state: State = State(),
    onAction: (Event) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clickable { onAction(Event.OnPenClicked) },
            imageVector = PencilIcon(state.selectedTool is Tool.Pen),
            contentDescription = null
        )
        Icon(
            modifier = Modifier.padding(horizontal = 16.dp),
            imageVector = BrushIcon,
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .clickable { onAction(Event.OnEraserClicked) },
            imageVector = EraseIcon(state.selectedTool is Tool.Eraser),
            contentDescription = null
        )
        Icon(
            modifier = Modifier.padding(horizontal = 16.dp),
            imageVector = InstrumentsIcon,
            contentDescription = null
        )
        ColorPickerIconToggle(state = state, onAction = onAction)
    }
}

@Composable
private fun ColorPickerIconToggle(
    state: State = State(),
    onAction: (Event) -> Unit = {}
) {
    var showMenu by remember { mutableStateOf(false) }

    val colors = listOf(Color.Green, Color.Red, Color.Gray, Color.Blue, Color.Magenta, Color.Yellow)
    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Image(
            imageVector = ColorPickerIcon(showMenu, state.strokeColor),
            contentDescription = "Color Picker",
            modifier = Modifier.clickable {
                showMenu = true
            }
        )
        if (showMenu) {
            Popup(
                alignment = Alignment.TopEnd,
                onDismissRequest = { showMenu = false },
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.7f), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    colors.forEach { color ->
                        IconButton(
                            onClick = {
                                showMenu = false
                                onAction(Event.OnColorChanged(color))
                            }
                        ) {
                            Icon(
                                imageVector = ColorPickerIcon(isChecked = false, color),
                                contentDescription = null,
                                tint = color
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun PreviewHeader() {
    Footer()
}