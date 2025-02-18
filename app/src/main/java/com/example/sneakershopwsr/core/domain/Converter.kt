package com.example.sneakershopwsr.core.domain


interface Converter<F, T> {
    fun convertFromTo(from: F): T
    fun convertToFrom(from: T): F
}
