package com.example.sneakershopwsr.shop.data.models


data class Product(
    val id: Int,
    val name: String,
    val price: Float,
    val description: String,
    val images: List<String>,
    val categoryId: Int,
)
