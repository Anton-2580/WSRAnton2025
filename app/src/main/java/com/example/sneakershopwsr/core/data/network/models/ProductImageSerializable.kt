package com.example.sneakershopwsr.core.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductImageSerializable(
    val id: Int?,
    val url: String,
    @SerialName("product_id") val productId: Int,
)
