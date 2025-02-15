package com.example.sneakershopwsr.auth.domain

import androidx.compose.foundation.text.input.TextFieldState

data class LoginRegisterState(
    val name: TextFieldState = TextFieldState(),
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val canSign: Boolean = false,
)