package com.example.sneakershopwsr.auth.presentation.registration

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
class AuthRegisterViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator,
    private val authInteractor: AuthInteractor,
): BaseAuthViewModel() {
    init {
        combine(
            snapshotFlow { loginRegisterState.value.email.text },
            snapshotFlow { loginRegisterState.value.password.text },
            snapshotFlow { loginRegisterState.value.name.text },
        ) { email, password, name ->
            _loginRegisterState.value = loginRegisterState.value.copy(
                canSign = userDataValidator.isPasswordValid(password.toString()) &&
                        userDataValidator.isNameValid(name.toString()) &&
                        userDataValidator.isEmailValid(email.toString()),
            )
        }.launchIn(viewModelScope)
    }

    fun signUp() {
        workWithData {
            authInteractor.signUp(
                email = loginRegisterState.value.email.text.toString(),
                password = loginRegisterState.value.password.text.toString(),
                name = loginRegisterState.value.name.text.toString(),
            )
        }
    }
}