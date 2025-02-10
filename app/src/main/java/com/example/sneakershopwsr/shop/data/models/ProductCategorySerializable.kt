package com.example.sneakershopwsr.shop.data.models

import kotlinx.serialization.Serializable


@Serializable
data class ProductCategorySerializable(
    val id: Int,
    val name: String,
)
