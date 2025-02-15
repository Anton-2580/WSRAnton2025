package com.example.sneakershopwsr.auth.presentation.login

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.auth.domain.UserDataValidator
import com.example.sneakershopwsr.auth.domain.repository.AuthInteractor
import com.example.sneakershopwsr.auth.presentation.BaseAuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject


@HiltViewModel
class AuthLoginViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator,
    private val authInteractor: AuthInteractor
): BaseAuthViewModel() {
    init {
        combine(
            snapshotFlow { loginRegisterState.value.email.text },
            snapshotFlow { loginRegisterState.value.password.text },
        ) { email, password ->
            _loginRegisterState.value = loginRegisterState.value.copy(
                canSign = password.isNotBlank() && userDataValidator.isEmailValid(email.toString())
            )
        }.launchIn(viewModelScope)
    }

    fun signIn() {
        workWithData {
            authInteractor.signIn(
                email = loginRegisterState.value.email.text.toString(),
                password = loginRegisterState.value.password.text.toString(),
            )
        }
    }
}