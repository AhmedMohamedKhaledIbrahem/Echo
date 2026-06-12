package com.echo.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.toInt(): Int {
    return with(LocalDensity.current) { this@toInt.toPx().toInt() }
}