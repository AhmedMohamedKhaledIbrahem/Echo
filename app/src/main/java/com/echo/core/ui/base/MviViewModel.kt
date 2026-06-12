package com.echo.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echo.core.ui.view.ViewEvent
import com.echo.core.ui.view.ViewIntent
import com.echo.core.ui.view.ViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Base class for implementing the MVI (Model-View-Intent) architecture
 * inside a [ViewModel].
 *
 * This abstract class provides a standardized and lifecycle-aware way
 * to manage:
 *
 * - UI state using [StateFlow]
 * - User actions through intents
 * - One-time UI events using [Channel]
 *
 * ## Generic Types
 *
 * @param S The type representing the UI state.
 *
 * @param I The type representing user intents/actions.
 *
 * @param E The type representing one-time UI events.
 *
 * ## Responsibilities
 *
 * - Holds and exposes immutable UI state via [state]
 * - Maintains internal mutable state through [_state]
 * - Dispatches one-time events through [event]
 * - Forces subclasses to define:
 *   - an [initialState]
 *   - intent handling via [onIntent]
 *
 * ## State Management
 *
 * - [_state] is backed by [MutableStateFlow]
 * - [state] is exposed as immutable [StateFlow]
 * - [stateIn] is used to share and cache state efficiently
 *
 * ## Event Management
 *
 * - One-time events such as:
 *   - navigation
 *   - snackbars
 *   - toasts
 *   - dialogs
 *   are emitted using [_event]
 *
 * - Events are exposed as Flow using [receiveAsFlow]
 *   to prevent unwanted re-consumption.
 *
 * ## Lifecycle Behavior
 *
 * Subclasses should:
 *
 * - Provide an implementation for [initialState]
 * - Handle user intents inside [onIntent]
 * - Update [_state] to emit new UI states
 * - Use [sendEvent] to emit one-time UI events
 *
 * Example:
 *
 * ```kotlin
 * class LoginViewModel :
 *     MviViewModel<LoginState, LoginIntent, LoginEvent>() {
 *
 *     override val initialState = LoginState()
 *
 *     override fun onIntent(intent: LoginIntent) {
 *         when(intent) {
 *             LoginIntent.LoginClicked -> login()
 *         }
 *     }
 *
 *     private fun login() {
 *         sendEvent(LoginEvent.NavigateToHome)
 *     }
 * }
 * ```
 */

abstract class MviViewModel<S : ViewState, I : ViewIntent,E : ViewEvent> : ViewModel() {
    /**
     * The initial UI state of the ViewModel.
     */
    abstract val initialState: S
    /**
     * Internal mutable state holder.
     */
    protected val _state: MutableStateFlow<S> by lazy { MutableStateFlow(initialState) }
    /**
     * Public immutable state exposed to the UI.
     */
    val state: StateFlow<S> by lazy {
        _state.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = initialState
        )
    }
    /**
     * Handles incoming user intents.
     *
     * @param intent The intent dispatched from the UI layer.
     */
    abstract fun onIntent(intent: I)

    /**
     * One-time events channel.
     */
    private val _event = Channel<E>(Channel.BUFFERED)

    /**
     * Public events flow.
     */
    val event = _event.receiveAsFlow()

    /**
     * Send one-time event to UI.
     */
    protected fun sendEvent(event: E) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

}
