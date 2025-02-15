package com.example.sneakershopwsr.auth.domain


data class UserInfo(
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val lastName: String? = null,
    val photo: String? = null,
    val address: String? = null,
    val password: String? = null,
)
