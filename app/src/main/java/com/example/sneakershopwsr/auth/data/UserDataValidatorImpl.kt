package com.example.sneakershopwsr.auth.data

import android.util.Patterns
import com.example.sneakershopwsr.auth.domain.UserDataValidator


class UserDataValidatorImpl: UserDataValidator {
    override fun isEmailValid(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}