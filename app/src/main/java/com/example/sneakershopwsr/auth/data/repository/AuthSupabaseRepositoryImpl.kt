package com.example.sneakershopwsr.auth.data.repository

import com.example.sneakershopwsr.auth.data.UserInfoSerializable
import com.example.sneakershopwsr.auth.data.toUserInfo
import com.example.sneakershopwsr.auth.data.toUserInfoSerializable
import com.example.sneakershopwsr.auth.domain.UserInfo
import com.example.sneakershopwsr.auth.domain.repository.AuthSupabaseRepository
import com.example.sneakershopwsr.core.data.network.SupabaseTables
import io.github.jan.supabase.SupabaseClient
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

        supabaseClient.from(SupabaseTables.USER).insert(UserInfoSerializable(
            name = name,
            email = email,
        ))

        sign
    }

    override suspend fun sendOTP(email: String) {
        withContext(dispatcher) {
            supabaseClient.auth.signInWith(OTP) {
                this.email = email
            }
        }
    }

    override suspend fun editUserInfo(info: UserInfo) {
        val data = info.toUserInfoSerializable()
        withContext(dispatcher) {
            supabaseClient.auth.updateUser {
                if (data.email !== null) email = data.email
                if (data.password !== null) password = data.password
                if (data.phone !== null) phone = data.phone
            }

            supabaseClient.from(SupabaseTables.USER).insert(data)
        }
    }

    override suspend fun getUserInfo(id: Int): UserInfo = withContext(dispatcher) {
        supabaseClient.from(SupabaseTables.USER).select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<UserInfoSerializable>().toUserInfo()
    }
}