package com.example.sneakershopwsr.auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.core.data.network.NetworkEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


open class BaseAuthViewModel: ViewModel() {
    protected val _loginRegisterState = mutableStateOf(LoginRegisterState())
    val loginRegisterState: State<LoginRegisterState> = _loginRegisterState

    private val _eventFlow = MutableSharedFlow<NetworkEvents>()
    val eventFlow: SharedFlow<NetworkEvents> = _eventFlow

    protected fun workWithData(content: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _eventFlow.emit(NetworkEvents.Loading)
                content()
                _eventFlow.emit(NetworkEvents.Successful)
            } catch (e: Throwable) {
                _eventFlow.emit(NetworkEvents.Error(e.message.toString()))
            }
        }
    }
}