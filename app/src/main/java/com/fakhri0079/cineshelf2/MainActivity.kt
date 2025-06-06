package com.fakhri0079.cineshelf2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fakhri0079.cineshelf2.ui.screen.MainScreen
import com.fakhri0079.cineshelf2.ui.theme.CineShelf2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineShelf2Theme {
                MainScreen()
            }
        }
    }
}

