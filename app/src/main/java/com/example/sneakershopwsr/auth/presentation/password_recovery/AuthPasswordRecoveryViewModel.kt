package com.example.sneakershopwsr.auth.presentation.password_recovery

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.auth.domain.UserDataValidator
import com.example.sneakershopwsr.auth.domain.repository.AuthSupabaseRepository
import com.example.sneakershopwsr.auth.presentation.BaseAuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import io.github.jan.supabase.auth.user.UserInfo as SupabaseUserInfo
import javax.inject.Inject


@HiltViewModel
class AuthPasswordRecoveryViewModel @Inject constructor(
    private val supabaseRepository: AuthSupabaseRepository<SupabaseUserInfo>,
    private val userDataValidator: UserDataValidator,
): BaseAuthViewModel() {
    init {
        snapshotFlow { loginRegisterState.value.email.text }.onEach { email ->
            _loginRegisterState.value = loginRegisterState.value.copy(
                canSign = userDataValidator.isEmailValid(email.toString())
            )
        }.launchIn(viewModelScope)
    }

    fun sendOtp() {
        workWithData<Exception> {
            supabaseRepository.sendOTP(loginRegisterState.value.email.text.toString())
        }
    }
}