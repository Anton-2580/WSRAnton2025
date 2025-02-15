package com.example.sneakershopwsr.auth.data

import com.example.sneakershopwsr.auth.domain.AuthInfo
import kotlinx.serialization.Serializable


@Serializable
data class AuthInfoSerializable(
    val id: String
)


fun AuthInfoSerializable.toAuthInfo() = AuthInfo(
    id = id
)

fun AuthInfo.toAuthInfoSerializable() = AuthInfoSerializable(
    id = id
)
