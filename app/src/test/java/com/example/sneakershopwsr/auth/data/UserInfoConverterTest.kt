package com.example.sneakershopwsr.auth.data

import com.example.sneakershopwsr.auth.domain.UserInfo
import com.example.sneakershopwsr.core.domain.Converter
import com.example.sneakershopwsr.core.domain.ConverterTest
import org.junit.Test


class UserInfoConverterTest: ConverterTest<UserInfoSerializable, UserInfo>() {
    override val converter: Converter<UserInfoSerializable, UserInfo> = UserInfoConverter()
    override var fromData: UserInfoSerializable = UserInfoSerializable()
    override var toData: UserInfo = UserInfo()


    @Test
    fun `test convertFromTo with nullability`() {
        super.testConvertFromTo()
    }

    @Test
    fun `test convertFromTo success`() {
        fromData = UserInfoSerializable(
            name = "name",
            email = "email",
            phone = "phone",
            lastName = "lastName",
            photo = "photo",
            address = "address",
            password = "password",
        )
        toData = UserInfo(
            name = "name",
            email = "email",
            phone = "phone",
            lastName = "lastName",
            photo = "photo",
            address = "address",
            password = "password",
        )

        super.testConvertFromTo()
    }

    @Test
    fun `test convertToFrom success`() {
        super.testConvertToFrom()
    }
}