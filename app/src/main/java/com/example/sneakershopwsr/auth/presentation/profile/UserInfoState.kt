package com.example.sneakershopwsr.auth.presentation.profile

import androidx.compose.foundation.text.input.TextFieldState
import com.example.sneakershopwsr.auth.domain.UserInfo

data class UserInfoState(
    val name: TextFieldState = TextFieldState(),
    val email: TextFieldState = TextFieldState(),
    val phone: TextFieldState = TextFieldState(),
    val lastName: TextFieldState = TextFieldState(),
    val photo: TextFieldState = TextFieldState(),
    val address: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isValid: Boolean = false,
)

fun UserInfoState.toUserInfo() = UserInfo(
    name = name.text.toString(),
    email = email.text.toString(),
    phone = phone.text.toString(),
    lastName = lastName.text.toString(),
    photo = photo.text.toString(),
    address = address.text.toString(),
    password = password.text.toString(),
)

fun UserInfo.toUserInfoState() = UserInfoState(
    name = TextFieldState(name.toString()),
    email = TextFieldState(email.toString()),
    phone = TextFieldState(phone.toString()),
    lastName = TextFieldState(lastName.toString()),
    photo = TextFieldState(photo.toString()),
    address = TextFieldState(address.toString()),
    password = TextFieldState(password.toString()),
    isValid = false,
)
