package com.example.start_wars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.start_wars.ui.screen.CreacionPelicula
import com.example.start_wars.ui.screen.EdicionPelicula
import com.example.start_wars.ui.theme.Start_warsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //CreacionPelicula()
            EdicionPelicula()

        }
    }
}
