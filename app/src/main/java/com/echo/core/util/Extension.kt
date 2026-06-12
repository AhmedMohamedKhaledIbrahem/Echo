package com.echo.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echo.core.error.Resource
import com.echo.core.error.RootError
import kotlinx.coroutines.launch

inline fun <D, E : RootError, R> Resource<D, E>.fold(
    onSuccess: (D) -> R,
    onFailure: (E) -> R,
): R = when (this) {
    is Resource.Success -> onSuccess(data)
    is Resource.Failure -> onFailure(error)
}

/**
 * Executes a suspend use case inside [viewModelScope] and handles its [Result].
 *
 * This function is designed to simplify calling suspend use cases from a [ViewModel]
 * by automatically launching a coroutine and delegating success and failure handling.
 *
 * @param D The type of successful result data.
 * @param E The type of error extending [RootError].
 * @param useCase A suspend lambda that returns a [Resource].
 * @param onSuccess Callback invoked when the result is [Resource.Success].
 * @param onFailure Callback invoked when the result is [Resource.Failure].
 */
inline fun <D, reified E : RootError> ViewModel.onUseCase(
    crossinline useCase: suspend () -> Resource<D, E>,
    crossinline onSuccess: suspend (D) -> Unit,
    crossinline onFailure: suspend (Resource.Failure<*, E>) -> Unit,
) {
    this.viewModelScope.launch {
        when (val result = useCase()) {
            is Resource.Success -> onSuccess(result.data)
            is Resource.Failure -> onFailure(result)
        }
    }
}