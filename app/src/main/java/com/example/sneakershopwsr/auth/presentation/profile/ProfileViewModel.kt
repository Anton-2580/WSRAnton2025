package com.example.sneakershopwsr.auth.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
class ProfileViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val userDataValidator: UserDataValidator,
): BaseAuthViewModel() {
    private val _userInfoState = mutableStateOf(UserInfoState())
    val userInfoState: State<UserInfoState> = _userInfoState

    init {
        getUserInfo()

        combine(
            snapshotFlow { userInfoState.value.name.text },
            snapshotFlow { userInfoState.value.email.text },
            snapshotFlow { userInfoState.value.phone.text },
            snapshotFlow { userInfoState.value.password.text },
        ) { name, email, phone, password ->
            _userInfoState.value = userInfoState.value.copy(
                isValid = userDataValidator.isNameValid(name.toString()) &&
                          userDataValidator.isPasswordValid(password.toString()) &&
                          userDataValidator.isEmailValid(email.toString()) &&
                          userDataValidator.isPhoneValid(phone.toString()),
            )

            if (userInfoState.value.isValid) {
                editUserInfo()
            }
        }.launchIn(viewModelScope)
    }

    private fun editUserInfo() {
        workWithData<Exception> {
            authInteractor.editUserInfo(userInfoState.value.toUserInfo())
        }

    }

    private fun getUserInfo() {
        workWithData<Exception> {
            _userInfoState.value = authInteractor.getUserInfo().toUserInfoState()
        }
    }
}