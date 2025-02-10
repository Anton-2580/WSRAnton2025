package com.example.sneakershopwsr.shop.data.models

import kotlinx.serialization.Serializable


@Serializable
data class ProductImageSerializable(
    val id: Int?,
    val url: String,
)
