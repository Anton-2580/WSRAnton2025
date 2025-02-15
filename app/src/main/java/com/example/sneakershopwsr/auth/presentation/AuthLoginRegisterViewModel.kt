package com.example.sneakershopwsr.auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.auth.domain.LoginRegisterState
import com.example.sneakershopwsr.auth.domain.repository.AuthInteractor
import com.example.sneakershopwsr.core.data.network.NetworkEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


open class AuthLoginRegisterViewModel(
    private val authInteractor: AuthInteractor,
): ViewModel() {
    protected val _loginRegisterState = mutableStateOf(LoginRegisterState())
    val loginRegisterState: State<LoginRegisterState> = _loginRegisterState

    private val _eventFlow = MutableSharedFlow<NetworkEvents>()
    val eventFlow: SharedFlow<NetworkEvents> = _eventFlow

    fun signIn() {
        viewModelScope.launch {
            try {
                _eventFlow.emit(NetworkEvents.Loading)
                authInteractor.signIn(
                    email = loginRegisterState.value.email.text.toString(),
                    password = loginRegisterState.value.password.text.toString(),
                )
                _eventFlow.emit(NetworkEvents.Successful)
            } catch (e: Throwable) {
                _eventFlow.emit(NetworkEvents.Error(e.message.toString()))
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            try {
                _eventFlow.emit(NetworkEvents.Loading)
                authInteractor.signUp(
                    email = loginRegisterState.value.email.text.toString(),
                    password = loginRegisterState.value.password.text.toString(),
                    name = loginRegisterState.value.name.text.toString(),
                )
                _eventFlow.emit(NetworkEvents.Successful)
            } catch (e: Throwable) {
                _eventFlow.emit(NetworkEvents.Error(e.message.toString()))
            }
        }
    }
}