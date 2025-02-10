package com.example.sneakershopwsr.shop.domain


data class ProductInfo(
    val id: Int?,
    val name: String,
    val price: Float,
    val description: String?,
    val categoryId: Int,
)
