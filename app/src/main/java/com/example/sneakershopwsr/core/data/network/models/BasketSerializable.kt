package com.example.sneakershopwsr.core.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BasketSerializable(
    val id: Int?,
    @SerialName("product_id") val productId: Int,
)
