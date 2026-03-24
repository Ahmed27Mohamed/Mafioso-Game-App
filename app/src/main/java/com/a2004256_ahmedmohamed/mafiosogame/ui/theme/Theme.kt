package com.a2004256_ahmedmohamed.mafiosogame.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF1E2A38),
    onPrimary = Color.White,
    secondary = Color(0xFF2A3A4A),
    onSecondary = Color.White,
    background = Color(0xFF121926),
    onBackground = Color.White,
    surface = Color(0xFF1B2532),
    onSurface = Color.White,
)

@Composable
fun MafiosoGameTheme(
    content: @Composable () -> Unit
) {
    androidx.compose.material3.MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = Typography,
        content = content
    )
}