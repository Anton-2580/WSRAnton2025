package com.example.sneakershopwsr.auth.data.repository

import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.auth.domain.repository.AuthInteractor
import com.example.sneakershopwsr.auth.domain.repository.AuthSupabaseRepository
import com.example.sneakershopwsr.core.domain.GlobalSessionKeys
import com.example.sneakershopwsr.core.domain.SessionStorage
import io.github.jan.supabase.auth.user.UserInfo


class AuthInteractorImpl(
    val authSupabaseRepository: AuthSupabaseRepository<UserInfo>,
    val authSessionStorage: SessionStorage<AuthInfo>,
): AuthInteractor {
    override suspend fun signIn(email: String, password: String) {
        authSupabaseRepository.signIn(email, password)
        authSessionStorage.set(GlobalSessionKeys.IS_LOGINED, AuthInfo(""))
    }

    override suspend fun signUp(email: String, password: String, name: String) {
        authSupabaseRepository.signUp(email, password, name)
    }
}