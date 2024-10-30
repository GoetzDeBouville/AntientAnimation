package org.zinchenkodev.antientanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.zinchenkodev.antientanimation.ui.Screen
import org.zinchenkodev.antientanimation.ui.theme.AntientAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AntientAnimationTheme(darkTheme = true) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
private fun GreetingPreview() {
    AntientAnimationTheme {
        Screen()
    }
}