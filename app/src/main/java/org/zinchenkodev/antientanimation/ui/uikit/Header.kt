package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.ActionBackIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.ActionForwardIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.AddFileIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.BinIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.LayersIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PauseIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PlayIcon


@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ActionBackIcon,
            contentDescription = null
        )
        Icon(
            modifier = Modifier.padding(start = 8.dp),
            imageVector = ActionForwardIcon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))

        CentralElements()

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = PauseIcon,
            contentDescription = null
        )
        Icon(
            modifier = Modifier.padding(start = 16.dp),
            imageVector = PlayIcon,
            contentDescription = null
        )
    }
}

@Composable
private fun CentralElements(modifier: Modifier = Modifier) {
    Row(modifier) {
        Icon(
            imageVector = BinIcon,
            contentDescription = null
        )
        Icon(
            imageVector = AddFileIcon,
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 16.dp)
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