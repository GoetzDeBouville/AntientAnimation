package org.zinchenkodev.antientanimation.ui.uikit

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
import org.zinchenkodev.antientanimation.presentation.models.Event
import org.zinchenkodev.antientanimation.presentation.models.State
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.ActionBackIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.ActionForwardIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.AddFileIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.BinIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.LayersIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PauseIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PlayIcon

@Suppress("t")
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
                if (state.backActionBackStack.isNotEmpty() && state.isPlaying.not()) {
                    onAction(Event.OnBackIconClicked)
                }
            },
            imageVector = ActionBackIcon(state.backActionBackStack.isNotEmpty() && state.isPlaying.not()),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    if (state.forwardActionBackStack.isNotEmpty() && state.isPlaying.not()) {
                        onAction(Event.OnForwardIconClicked)
                    }
                },
            imageVector = ActionForwardIcon(state.forwardActionBackStack.isNotEmpty() && state.isPlaying.not()),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))

        CentralElements(
            state = state,
            onAction = onAction
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            imageVector = PauseIcon(state.isPlaying),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onAction(Event.OnPauseClicked)
                }
        )
        val isActivePlay = state.isPlaying.not() && state.framesNumber > 1

        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable {
                    if (isActivePlay) {
                        onAction(Event.OnPlayClicked)
                    }
                },
            imageVector = PlayIcon(isActivePlay),
            contentDescription = null
        )
    }
}

@Composable
private fun CentralElements(
    state: State = State(),
    modifier: Modifier = Modifier,
    onAction: (Event) -> Unit = {}
) {
    Row(modifier) {
        Icon(
            modifier = Modifier
                .clickable {
                    if (state.isPlaying.not()) {
                        onAction(Event.OnClearFrameClicked)
                    }
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
                    if (state.isPlaying.not()) {
                        onAction(Event.OnCreateNewFrameClicked)
                    }
                }
        )
        Image(
            imageVector = LayersIcon(false),
            contentDescription = null
        )
    }
}


@Composable
@Preview
private fun PreviewHeader() {
    Header()
}