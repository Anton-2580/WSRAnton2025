package com.example.sneakershopwsr.auth.data

import android.content.SharedPreferences
import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.core.domain.SessionStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class AuthSessionStorage(
    private val sharedPreferences: SharedPreferences,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): SessionStorage<AuthInfo> {
    override suspend fun get(key: String): AuthInfo? = withContext(dispatcher) {
        val json = sharedPreferences.getString(key, null)

        json?.let {
            Json.decodeFromString<AuthInfoSerializable>(it).toAuthInfo()
        }
    }

    override suspend fun set(key: String, info: AuthInfo) {
        withContext(dispatcher) {
            val json = Json.encodeToString(info.toAuthInfoSerializable())
            sharedPreferences.edit().putString(key, json).commit()
        }
    }

    override suspend fun del(key: String) {
        withContext(dispatcher) {
            sharedPreferences.edit().remove(key).commit()
        }
    }
}
