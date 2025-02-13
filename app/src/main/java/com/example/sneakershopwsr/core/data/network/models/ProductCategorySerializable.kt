package com.example.sneakershopwsr.core.data.network.models

import kotlinx.serialization.Serializable


@Serializable
data class ProductCategorySerializable(
    val id: Int,
    val name: String,
)
