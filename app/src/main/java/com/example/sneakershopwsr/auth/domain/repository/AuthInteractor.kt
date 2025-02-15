package com.example.sneakershopwsr.auth.domain.repository

import com.example.sneakershopwsr.auth.domain.UserInfo


interface AuthInteractor {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, name: String)

    suspend fun editUserInfo(info: UserInfo)
    suspend fun getUserInfo(): UserInfo
}