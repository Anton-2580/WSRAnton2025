package com.example.sneakershopwsr.auth.presentation.password_recovery

import androidx.compose.foundation.text.input.TextFieldState


data class OtpState(
    val code: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val changingPassword: Boolean = true,
)