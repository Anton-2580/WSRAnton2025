package com.example.sneakershopwsr.auth.data

import androidx.core.util.PatternsCompat
import com.example.sneakershopwsr.auth.domain.UserDataValidator


class UserDataValidatorImpl: UserDataValidator {
    override fun isEmailValid(email: String): Boolean =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    override fun isPasswordValid(password: String): Boolean =
        password.isNotBlank()

    override fun isNameValid(name: String): Boolean =
        name.isNotBlank()

    override fun isPhoneValid(phone: String): Boolean =
        phone.isNotBlank()
}