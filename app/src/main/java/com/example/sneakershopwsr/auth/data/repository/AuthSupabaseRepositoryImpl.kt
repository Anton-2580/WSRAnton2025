package com.example.sneakershopwsr.auth.data.repository

import com.example.sneakershopwsr.auth.domain.repository.AuthSupabaseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo


class AuthSupabaseRepositoryImpl(
    val supabaseClient: SupabaseClient,
): AuthSupabaseRepository<UserInfo> {
    override suspend fun signIn(email: String, password: String) {
        supabaseClient.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    override suspend fun signUp(email: String, password: String, name: String): UserInfo? =
        supabaseClient.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }

}