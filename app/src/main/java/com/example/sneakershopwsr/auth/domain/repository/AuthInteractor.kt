package com.example.sneakershopwsr.auth.domain.repository


interface AuthInteractor {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, name: String)
}