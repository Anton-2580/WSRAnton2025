package com.example.sneakershopwsr.auth.data

import com.example.sneakershopwsr.auth.domain.UserInfo
import com.example.sneakershopwsr.core.domain.Converter


class UserInfoConverter: Converter<UserInfoSerializable, UserInfo> {
    override fun convertFromTo(from: UserInfoSerializable): UserInfo = UserInfo(
        name = from.name,
        email = from.email,
        phone = from.phone,
        lastName = from.lastName,
        photo = from.photo,
        address = from.address,
        password = from.password,
    )

    override fun convertToFrom(from: UserInfo): UserInfoSerializable = UserInfoSerializable(
        name = from.name,
        email = from.email,
        phone = from.phone,
        lastName = from.lastName,
        photo = from.photo,
        address = from.address,
        password = from.password,
    )
}