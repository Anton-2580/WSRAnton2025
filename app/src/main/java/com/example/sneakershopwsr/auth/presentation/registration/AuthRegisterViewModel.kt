package com.example.sneakershopwsr.auth.presentation.registration

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.auth.domain.UserDataValidator
import com.example.sneakershopwsr.auth.domain.repository.AuthInteractor
import com.example.sneakershopwsr.auth.presentation.AuthLoginRegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject


@HiltViewModel
class AuthRegisterViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator,
    authInteractor: AuthInteractor,
): AuthLoginRegisterViewModel(
    authInteractor,
) {
    init {
        combine(
            snapshotFlow { loginRegisterState.value.email.text },
            snapshotFlow { loginRegisterState.value.password.text },
            snapshotFlow { loginRegisterState.value.name.text },
        ) { email, password, name ->
            _loginRegisterState.value = loginRegisterState.value.copy(
                canSign = password.isNotBlank() && name.isNotBlank() &&
                        userDataValidator.isEmailValid(email.toString())
            )
        }.launchIn(viewModelScope)
    }
}