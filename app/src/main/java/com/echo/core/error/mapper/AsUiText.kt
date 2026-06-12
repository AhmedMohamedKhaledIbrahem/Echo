package com.echo.core.error.mapper

import com.echo.R
import com.echo.core.error.DomainError
import com.echo.core.error.DomainError.Local.EMPTY_DATA
import com.echo.core.error.DomainError.Local.FILE_NOT_FOUND
import com.echo.core.error.DomainError.Local.INVALID_DATA
import com.echo.core.error.DomainError.UNKNOWN.UNKNOWN
import com.echo.core.error.Resource
import com.echo.core.error.RootError
import com.echo.core.ui.UiText
import com.echo.core.ui.UiText.DynamicString
import com.echo.core.ui.UiText.StringResource

fun DomainError.asUiText(): UiText {
    return when (this) {

        EMPTY_DATA -> StringResource(R.string.error_empty_data)
        FILE_NOT_FOUND -> StringResource(R.string.error_file_not_found)
        INVALID_DATA -> StringResource(R.string.error_invalid_data)
        UNKNOWN -> StringResource(R.string.error_unknown)
    }
}

inline fun <reified E : RootError> Resource.Failure<*, E>.asUiTextOrDefault(
    default: UiText = DynamicString(""),
): UiText {
    return when (val error = this.error) {
        is DomainError -> error.asUiText()
        else -> default
    }
}