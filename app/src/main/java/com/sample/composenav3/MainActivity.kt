package com.sample.composenav3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sample.composenav3.navigation.RootNavigation
import com.sample.composenav3.ui.theme.ComposeNav3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNav3Theme {
                RootNavigation()
            }
        }
    }
}
