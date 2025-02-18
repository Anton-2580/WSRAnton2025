package com.example.sneakershopwsr.auth.data

import org.junit.Test


/**
 * Test to [UserDataValidatorImpl]
 */
class UserDataValidatorImplTest {
    private val userDataValidator = UserDataValidatorImpl()

    @Test
    fun `test isEmailValid success`() {
        val email = "a@b.c"
        
        val actual = userDataValidator.isEmailValid(email)
        val expected = true
        assert(actual == expected)
    }

    @Test
    fun `test isEmailValid invalid name`() {
        val email = "a bc@b.c"

        val actual = userDataValidator.isEmailValid(email)
        val expected = false
        assert(actual == expected)
    }

    @Test
    fun `test isEmailValid invalid provider`() {
        val email = "a@b b.c"

        val actual = userDataValidator.isEmailValid(email)
        val expected = false
        assert(actual == expected)
    }

    @Test
    fun `test isEmailValid invalid domain`() {
        val email = "a@b.c om"

        val actual = userDataValidator.isEmailValid(email)
        val expected = false
        assert(actual == expected)
    }

    @Test
    fun `test isPasswordValid is not blank`() {
        val password = "1"

        val actual = userDataValidator.isPasswordValid(password)
        val expected = true

        assert(actual == expected)
    }

    @Test
    fun `test isPasswordValid is blank`() {
        val password = ""

        val actual = userDataValidator.isPasswordValid(password)
        val expected = false

        assert(actual == expected)
    }

    @Test
    fun `test isPhoneValid success`() {
        val phone = "81234567890"

        val actual = userDataValidator.isPhoneValid(phone)
        val expected = true
        assert(actual == expected)
    }
    
}