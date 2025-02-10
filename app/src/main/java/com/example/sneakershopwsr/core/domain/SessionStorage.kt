package com.example.sneakershopwsr.core.domain


interface SessionStorage<T> {
    suspend fun get(key: String): T?
    suspend fun set(key: String, info: T)
    suspend fun del(key: String)
}
