package com.example.sneakershopwsr.shop.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductInfoSerializable(
    val id: Int?,
    val name: String,
    val price: Float,
    val description: String?,
    @SerialName("category_id") val categoryId: Int,
)
