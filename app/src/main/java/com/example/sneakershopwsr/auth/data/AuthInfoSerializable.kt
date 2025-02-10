package com.example.sneakershopwsr.auth.data

import com.example.sneakershopwsr.auth.domain.AuthInfo
import kotlinx.serialization.Serializable


@Serializable
data class AuthInfoSerializable(
    val token: String
)


fun AuthInfoSerializable.toAuthInfo() = AuthInfo(
    token = token
)

fun AuthInfo.toAuthInfoSerializable() = AuthInfoSerializable(
    token = token
)
