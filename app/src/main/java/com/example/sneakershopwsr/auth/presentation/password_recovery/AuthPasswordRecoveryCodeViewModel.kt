package com.example.sneakershopwsr.auth.presentation.password_recovery

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.sneakershopwsr.auth.domain.UserInfo
import com.example.sneakershopwsr.auth.domain.repository.AuthSupabaseRepository
import com.example.sneakershopwsr.core.navigation.graphs.AuthPasswordRecoveryCode
import com.example.sneakershopwsr.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import io.github.jan.supabase.auth.user.UserInfo as SupabaseUserInfo
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class AuthPasswordRecoveryCodeViewModel @Inject constructor(
    private val authSupabaseRepository: AuthSupabaseRepository<SupabaseUserInfo>,
    savedStateHandle: SavedStateHandle,
): BaseViewModel() {
    private val _otpState = mutableStateOf(OtpState())
    val otpState: State<OtpState> = _otpState
    private val email: String = savedStateHandle.toRoute<AuthPasswordRecoveryCode>().email

    init {
        val sendToVerify: suspend (String) -> Unit = { text ->
            workWithData<Exception> {
                authSupabaseRepository.verifyOTP(
                    email = email,
                    otp = text,
                )
            }
        }

        snapshotFlow { otpState.value.code.text }.onEach { text ->
            if (text.length == MAX_TEXT_LENGTH) {
                sendToVerify(text.toString())
            }
        }.launchIn(viewModelScope)
    }

    fun generatePassword() {
        val text = otpState.value.password.text.toString()

        val password = buildString {
            text.forEach {
                append(it.plus(Random.nextInt(-100, 100)))
            }
        }

        _otpState.value = otpState.value.copy(
            password = TextFieldState(password),
            changingPassword = false,
        )

        workWithData<Exception> {
            authSupabaseRepository.editUserInfo(
                info = UserInfo(password = password),
                filter = UserInfo(email = email)
            )
        }
    }


    val maxTextLength = MAX_TEXT_LENGTH

    companion object {
        const val MAX_TEXT_LENGTH = 6
    }
}
