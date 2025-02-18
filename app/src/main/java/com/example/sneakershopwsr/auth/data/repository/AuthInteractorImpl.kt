package com.example.sneakershopwsr.auth.data.repository

import android.content.res.Resources
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.auth.domain.UserInfo
import com.example.sneakershopwsr.auth.domain.repository.AuthInteractor
import com.example.sneakershopwsr.auth.domain.repository.AuthSupabaseRepository
import com.example.sneakershopwsr.core.domain.GlobalSessionKeys
import com.example.sneakershopwsr.core.domain.SessionStorage
import io.github.jan.supabase.auth.user.UserInfo as SupabaseUserInfo


class AuthInteractorImpl(
    val authSupabaseRepository: AuthSupabaseRepository<SupabaseUserInfo>,
    val authSessionStorage: SessionStorage<AuthInfo>,
): AuthInteractor {
    override suspend fun signIn(email: String, password: String) {
        authSupabaseRepository.signIn(email, password)
        authSessionStorage.set(GlobalSessionKeys.IS_LOGINED, AuthInfo("true"))
    }

    override suspend fun signUp(email: String, password: String, name: String) {
        val info = authSupabaseRepository.signUp(email, password, name)

        if (info !== null) {
            authSessionStorage.set(GlobalSessionKeys.USER_ID, AuthInfo(info.id))
        }
    }

    override suspend fun editUserInfo(info: UserInfo) {
        authSupabaseRepository.editUserInfo(info, UserInfo(email = info.email))
    }

    override suspend fun getUserInfo(): UserInfo {
        val id = authSessionStorage.get(GlobalSessionKeys.USER_ID)

        if (id !== null) {
            return authSupabaseRepository.getUserInfo(id.id.toInt())
        } else {
            throw Exception(Resources.getSystem().getString(R.string.not_found_user_data))
        }
    }


}
