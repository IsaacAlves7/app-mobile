package com.androidexpress.githubstars.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = AndroidGreen,
    secondary = KotlinPurple,
    background = BackgroundDark,
    surface = SurfaceDark,
    surfaceVariant = SurfaceDarkVariant,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onPrimary = BackgroundDark,
)

private val LightColors = lightColorScheme(
    primary = AndroidGreen,
    secondary = KotlinPurple,
)

@Composable
fun GitHubStarsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
