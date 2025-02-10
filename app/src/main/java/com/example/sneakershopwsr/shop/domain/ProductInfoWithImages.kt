package com.example.sneakershopwsr.shop.domain


data class ProductInfoWithImages(
    val id: Int,
    val name: String,
    val price: Float,
    val description: String?,
    val categoryId: Int,

    val images: List<ProductImage>,
)
