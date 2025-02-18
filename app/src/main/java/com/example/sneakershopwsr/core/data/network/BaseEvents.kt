package com.example.sneakershopwsr.core.data.network


sealed interface BaseEvents {
    data class Error(val message: String): BaseEvents
    data object Loading: BaseEvents
    data object Successful: BaseEvents
}