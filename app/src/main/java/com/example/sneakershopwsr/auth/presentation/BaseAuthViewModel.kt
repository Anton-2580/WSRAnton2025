package com.example.sneakershopwsr.auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.sneakershopwsr.core.presentation.BaseViewModel


open class BaseAuthViewModel: BaseViewModel() {
    protected val _loginRegisterState = mutableStateOf(LoginRegisterState())
    val loginRegisterState: State<LoginRegisterState> = _loginRegisterState
}
