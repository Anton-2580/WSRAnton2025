package com.example.sneakershopwsr.auth.data.repository

import com.example.sneakershopwsr.auth.data.UserInfoSerializable
import com.example.sneakershopwsr.auth.domain.UserInfo
import com.example.sneakershopwsr.auth.domain.repository.AuthSupabaseRepository
import com.example.sneakershopwsr.core.data.network.SupabaseTables
import com.example.sneakershopwsr.core.domain.Converter
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.jan.supabase.auth.user.UserInfo as SupabaseUserInfo
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AuthSupabaseRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val converter: Converter<UserInfoSerializable, UserInfo>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): AuthSupabaseRepository<SupabaseUserInfo> {
    override suspend fun signIn(email: String, password: String) {
        withContext(dispatcher) {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
        }
    }

    override suspend fun signUp(email: String, password: String, name: String): SupabaseUserInfo? = withContext(dispatcher) {
        val sign = supabaseClient.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }

        supabaseClient.from(SupabaseTables.USER).update({
            set("email", email)
            set("password", password)
        })

        sign
    }

    override suspend fun sendOTP(email: String) {
        withContext(dispatcher) {
            supabaseClient.auth.signInWith(OTP) {
                this.email = email
                this.createUser = false
            }
        }
    }

    override suspend fun verifyOTP(email: String, otp: String) {
        withContext(dispatcher) {
            supabaseClient.auth.verifyEmailOtp(
                type = OtpType.Email.EMAIL,
                email = email,
                token = otp,
            )
        }
    }

    override suspend fun editUserInfo(info: UserInfo, filter: UserInfo) {
        val data = converter.convertToFrom(info)
        withContext(dispatcher) {
            supabaseClient.auth.updateUser {
                if (data.email !== null) email = data.email
                if (data.password !== null) password = data.password
                if (data.phone !== null) phone = data.phone
            }

            val updateFields = { data: UserInfo, doSome: (String, Any) -> Unit ->
                if (data.name !== null) doSome("name", data.name)
                if (data.email !== null) doSome("email", data.email)
                if (data.phone !== null) doSome("phone", data.phone)
                if (data.address !== null) doSome("address", data.address)
                if (data.lastName !== null) doSome("last_name", data.lastName)
                if (data.photo !== null) doSome("last_name", data.photo)
            }

            supabaseClient.from(SupabaseTables.USER).update({
                updateFields(info) { str, value -> set(str, value) }
            }) {
                filter {
                    updateFields(filter) { str, value -> eq(str, value) }
                }
            }
        }
    }

    override suspend fun getUserInfo(id: Int): UserInfo = withContext(dispatcher) {
        val info = supabaseClient.from(SupabaseTables.USER).select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<UserInfoSerializable>()
        converter.convertFromTo(info)
    }
}