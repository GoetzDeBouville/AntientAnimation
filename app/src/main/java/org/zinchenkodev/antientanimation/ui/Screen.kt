package org.zinchenkodev.antientanimation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.zinchenkodev.antientanimation.ui.theme.AntientAnimationTheme
import org.zinchenkodev.antientanimation.ui.uikit.Body
import org.zinchenkodev.antientanimation.ui.uikit.Footer
import org.zinchenkodev.antientanimation.ui.uikit.Header

@Composable
fun Screen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Header(modifier = Modifier.padding(horizontal = 16.dp))
        Body(modifier = Modifier
            .weight(1f)
            .padding(vertical = 32.dp))
        Footer(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    AntientAnimationTheme {
        Screen()
    }
}