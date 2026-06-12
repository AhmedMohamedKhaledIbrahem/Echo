package com.echo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.echo.core.ui.Dimens
import com.echo.core.ui.GradientScheme
import com.echo.core.ui.LocalGradient
import com.echo.core.ui.LocalSpacing

private val DefaultScheme =
    lightColorScheme(
        primary = Primary30,
        primaryContainer = Primary50,
        onPrimary = Primary100,
        onPrimaryContainer = Primary95,
        secondary = Secondary30,
        secondaryContainer = Secondary50,
        background = NeutralVariant99,
        surface = Primary100,
        inverseOnSurface = Secondary80,
        onSurface = NeutralVariant10,
        onSurfaceVariant = NeutralVariant30,
        outline = NeutralVariant50,
        outlineVariant = NeutralVariant80,
        surfaceVariant = SurfaceVariant,
        surfaceTint = SurfaceTint12,
        inverseSurface = Secondary95,
        onErrorContainer = Error20,
        errorContainer = Error95,
        onError = Error100,
    )
@Composable
fun EchoTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalGradient provides GradientScheme(),
        LocalSpacing provides Dimens(),
    ) {
        MaterialTheme(
            colorScheme = DefaultScheme,
            shapes = shapes,
            typography = Typography,
            content = content,
        )
    }
}