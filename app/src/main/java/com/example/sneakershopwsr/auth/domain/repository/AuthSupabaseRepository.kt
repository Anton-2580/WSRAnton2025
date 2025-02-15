package com.example.sneakershopwsr.auth.domain.repository


interface AuthSupabaseRepository<T> {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, name: String): T?
}