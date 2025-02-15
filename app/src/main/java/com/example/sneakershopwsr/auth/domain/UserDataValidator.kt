package com.example.sneakershopwsr.auth.domain

interface UserDataValidator {
    fun isEmailValid(email: String): Boolean
}