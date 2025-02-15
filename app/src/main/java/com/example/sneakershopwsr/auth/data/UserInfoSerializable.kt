package com.example.sneakershopwsr.auth.data

import com.example.sneakershopwsr.auth.domain.UserInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserInfoSerializable(
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    val photo: String? = null,
    val address: String? = null,
    val password: String? = null,
)

fun UserInfoSerializable.toUserInfo() = UserInfo(
    name = name,
    email = email,
    phone = phone,
    lastName = lastName,
    photo = photo,
    address = address,
    password = password
)


fun UserInfo.toUserInfoSerializable() = UserInfoSerializable(
    name = name,
    email = email,
    phone = phone,
    lastName = lastName,
    photo = photo,
    address = address,
    password = password
)
