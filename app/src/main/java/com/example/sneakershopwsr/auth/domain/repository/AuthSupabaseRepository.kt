package com.example.sneakershopwsr.auth.domain.repository

import com.example.sneakershopwsr.auth.domain.UserInfo


interface AuthSupabaseRepository<T> {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, name: String): T?
    suspend fun sendOTP(email: String)
    suspend fun verifyOTP(email: String, otp: String)

    suspend fun editUserInfo(info: UserInfo, filter: UserInfo)
    suspend fun getUserInfo(id: Int): UserInfo
}