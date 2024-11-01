package org.zinchenkodev.antientanimation.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.zinchenkodev.antientanimation.ui.theme.AntientAnimationTheme
import org.zinchenkodev.antientanimation.ui.uikit.Body
import org.zinchenkodev.antientanimation.ui.uikit.Footer
import org.zinchenkodev.antientanimation.ui.uikit.Header

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Column(modifier = modifier) {
        Header(
            modifier = Modifier.padding(horizontal = 16.dp),
            state = state.value,
            onAction = viewModel::accept
        )
        Body(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 32.dp),
            state = state.value,
            onAction = viewModel::accept
        )
        Footer(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            state = state.value,
            onAction = viewModel::accept
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    AntientAnimationTheme {
        Screen()
    }
}