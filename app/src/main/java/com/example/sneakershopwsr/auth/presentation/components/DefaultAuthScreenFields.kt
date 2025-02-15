package com.example.sneakershopwsr.auth.presentation.components

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.ui.theme.CloseEyeIcon
import com.example.sneakershopwsr.ui.theme.OpenEyeIcon


@Composable
fun EmailTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
) {
    AuthSecureTextField(
        state = state,
        modifier = modifier,
        textFieldName = stringResource(R.string.email),
        visibleTextObfuscationMode = true,
        keyboardType = KeyboardType.Email,
    )
}


@Composable
fun PasswordTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    textBottomAction: String? = stringResource(R.string.recovery_password),
    onTextBottomActionClick: () -> Unit = {},
) {
    val visiblePassword = remember { mutableStateOf(false) }
    AuthSecureTextField(
        state = state,
        modifier = modifier,
        onTextBottomActionClick = onTextBottomActionClick,
        textFieldName = stringResource(R.string.password),
        textBottomAction = textBottomAction,
        visibleTextObfuscationMode = visiblePassword.value,
        icon = if (visiblePassword.value) OpenEyeIcon else CloseEyeIcon,
        onIconClick = { visiblePassword.value = !visiblePassword.value }
    )
}