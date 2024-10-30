package org.zinchenkodev.antientanimation.ui.uikit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.AddFileIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.BinIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.BrushIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.ColorPickerIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.EraseIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.InstrumentsIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.LayersIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PencilIcon
import org.zinchenkodev.antientanimation.ui.uikit.iconresources.PlayIcon


@Composable
fun Footer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = PencilIcon,
            contentDescription = null
        )
        Icon(
            modifier = Modifier.padding(horizontal = 16.dp),
            imageVector = BrushIcon,
            contentDescription = null
        )

        Icon(
            imageVector = EraseIcon,
            contentDescription = null
        )
        Icon(
            modifier = Modifier.padding(horizontal = 16.dp),
            imageVector = InstrumentsIcon,
            contentDescription = null
        )
        Icon(
            imageVector = ColorPickerIcon,
            contentDescription = null
        )
    }
}

@Composable
@Preview
private fun PreviewHeader() {
    Footer()
}