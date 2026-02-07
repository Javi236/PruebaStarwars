package com.example.start_wars.composables

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimens (
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 24.dp,
    val cardElevation: Dp = 4.dp
)

val LocalAppDimens = compositionLocalOf { AppDimens() }