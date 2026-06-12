package com.echo.core.component.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.echo.R
import com.echo.core.component.PrimaryButton
import com.echo.core.component.SecondaryButton
import com.echo.core.ui.spacing

@Composable
fun EditorBottomButtons(
    primaryButtonText: String,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
    primaryButtonEnabled: Boolean = true,
    primaryLeadingIcon: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spaceMedium),
    ) {
        SecondaryButton(
            modifier = Modifier.fillMaxHeight(),
            text = stringResource(id = R.string.button_text_cancel),
            onClick = onCancelClick,
        )

        PrimaryButton(
            modifier = Modifier.weight(1f),
            text = primaryButtonText,
            onClick = onConfirmClick,
            enabled = primaryButtonEnabled,
            leadingIcon = primaryLeadingIcon,
        )
    }
}