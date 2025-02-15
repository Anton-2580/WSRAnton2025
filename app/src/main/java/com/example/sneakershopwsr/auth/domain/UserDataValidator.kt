package com.example.sneakershopwsr.auth.domain

interface UserDataValidator {
    fun isEmailValid(email: String): Boolean
    fun isPasswordValid(password: String): Boolean
    fun isNameValid(name: String): Boolean
    fun isPhoneValid(phone: String): Boolean
}