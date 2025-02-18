package com.example.sneakershopwsr.auth.data

import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.core.domain.Converter


class AuthInfoConverter: Converter<AuthInfoSerializable, AuthInfo> {
    override fun convertFromTo(from: AuthInfoSerializable): AuthInfo = AuthInfo(
        id = from.id,
    )

    override fun convertToFrom(from: AuthInfo): AuthInfoSerializable = AuthInfoSerializable(
        id = from.id,
    )
}