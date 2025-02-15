package com.example.sneakershopwsr.core.data.network


sealed interface NetworkEvents {
    data class Error(val message: String): NetworkEvents
    data object Loading: NetworkEvents
    data object Successful: NetworkEvents
}