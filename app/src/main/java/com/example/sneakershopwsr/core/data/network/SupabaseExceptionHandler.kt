package com.example.sneakershopwsr.core.data.network

import android.util.Log
import io.github.jan.supabase.exceptions.HttpRequestException


suspend fun supabaseExceptionHandler(
    query: suspend () -> Unit,
) {
    try {
        query()
    } catch (e: HttpRequestException) {
        Log.e("Abra", "$e, ${e.message}")
    }
}