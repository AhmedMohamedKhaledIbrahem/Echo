package com.echo.core.navigation

import androidx.navigation3.runtime.NavKey
import com.echo.core.constant.UNDEFINED_ECHO_ID
import com.echo.core.ui.view.ViewEvent
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : ViewEvent, NavKey {
    @Serializable
    data object HomeScreen : Route, ViewEvent, NavKey

    @Serializable
    data object SettingScreen : Route, ViewEvent, NavKey

    @Serializable
    data class EditorScreen(val id: Long = UNDEFINED_ECHO_ID, val audioFilePath: String) : Route,
        ViewEvent, NavKey
}