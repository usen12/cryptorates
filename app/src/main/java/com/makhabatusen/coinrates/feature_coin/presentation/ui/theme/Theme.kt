package com.makhabatusen.coinrates.feature_coin.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary          = Blue400,
    onPrimary        = Navy900,
    primaryContainer = Navy700,
    background       = Navy900,
    onBackground     = White,
    surface          = Navy800,
    onSurface        = White,
    surfaceVariant   = Navy700,
    onSurfaceVariant = Grey400,
    outline          = Navy600,
    secondary        = Green400,
    onSecondary      = Navy900,
    tertiary         = Amber400,
    onTertiary       = Navy900,
    error            = Red400,
    onError          = Navy900
)

private val LightColorScheme = lightColorScheme(
    primary          = Blue600,
    onPrimary        = Color.White,
    primaryContainer = LightSurfaceVar,
    background       = LightBg,
    onBackground     = Navy900,
    surface          = LightSurface,
    onSurface        = Navy900,
    surfaceVariant   = LightSurfaceVar,
    onSurfaceVariant = Grey400,
    outline          = Grey200,
    secondary        = Color(0xFF059669),
    onSecondary      = Color.White,
    tertiary         = Color(0xFFD97706),
    error            = Color(0xFFDC2626),
    onError          = Color.White
)

@Composable
fun CoinRatesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}