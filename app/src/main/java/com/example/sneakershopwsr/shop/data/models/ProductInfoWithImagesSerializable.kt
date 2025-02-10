package com.example.sneakershopwsr.shop.data.models

import kotlinx.serialization.Serializable


@Serializable
class ProductInfoWithImagesSerializable(
    val id: Int,
    val name: String,
    val price: Float,
    val description: String?,
    val categoryId: Int,

    val images: List<ProductImageSerializable>,
)
