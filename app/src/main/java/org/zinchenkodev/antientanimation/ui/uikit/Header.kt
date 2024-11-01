package org.zinchenkodev.antientanimation.ui.uikit

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.zinchenkodev.antientanimation.models.Event
import org.zinchenkodev.antientanimation.models.State
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.ActionBackIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.ActionForwardIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.AddFileIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.BinIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.LayersIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PauseIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PlayIcon

@Composable
fun Header(
    modifier: Modifier = Modifier,
    state: State = State(),
    onAction: (Event) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.clickable {
                Log.i("Header", "Back icon clicked")
                onAction(Event.OnBackIconClicked)
            },
            imageVector = ActionBackIcon(state.backAction.isNotEmpty()),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    Log.i("Header", "Back icon clicked")
                    onAction(Event.OnForwardIconClicked)
                },
            imageVector = ActionForwardIcon(state.nextAction.isNotEmpty()),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))

        CentralElements(
            onAction = onAction
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            imageVector = PauseIcon(state.onPlay),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onAction(Event.OnPauseClicked)
                }
        )
        val isActivePlay = state.onPlay.not() && state.frameList.size > 1

        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable {
                    onAction(Event.OnPlayClicked)
                },
            imageVector = PlayIcon(isActivePlay),
            contentDescription = null
        )
    }
}

@Composable
private fun CentralElements(
    modifier: Modifier = Modifier,
    onAction: (Event) -> Unit = {}
) {
    Row(modifier) {
        Icon(
            modifier = Modifier
                .clickable {
                    onAction(Event.OnClearFrameClicked)
                },
            imageVector = BinIcon,
            contentDescription = null
        )
        Icon(
            imageVector = AddFileIcon,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable {
                    onAction(Event.OnCreateNewFrameClicked)
                }
        )
        Icon(
            imageVector = LayersIcon,
            contentDescription = null
        )
    }
}


@Composable
@Preview
private fun PreviewHeader() {
    Header()
}