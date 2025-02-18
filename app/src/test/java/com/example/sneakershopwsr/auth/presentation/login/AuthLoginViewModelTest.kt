package com.example.sneakershopwsr.auth.presentation.login

import com.example.sneakershopwsr.auth.domain.UserDataValidator
import com.example.sneakershopwsr.auth.domain.repository.AuthInteractor
import io.mockk.mockk
import org.junit.Test


class AuthLoginViewModelTest {

    private val userDataValidator: UserDataValidator = mockk()
    private val authInteractor: AuthInteractor = mockk()

    private val viewModel: AuthLoginViewModel = AuthLoginViewModel(
        userDataValidator = userDataValidator,
        authInteractor = authInteractor,
    )

    @Test
    fun `test errorDialog because invalid Email`() {
        viewModel.loginRegisterState.value.email.edit {
            append("adsf asdf@g.c")
        }

        val actual = viewModel.loginRegisterState.value.canSign
        val expected = false

        assert(actual == expected)
    }

    @Test
    fun `test errorDialog because invalid Password`() {
        viewModel.loginRegisterState.value.password.edit {
            append("adsf asdf@g.c")
        }

        val actual = viewModel.loginRegisterState.value.canSign
        val expected = false

        assert(actual == expected)
    }
}