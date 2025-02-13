package com.example.sneakershopwsr.core.data.network.models

import com.example.sneakershopwsr.core.data.network.SupabaseTables
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class ProductInfoWithImagesSerializable(
    val id: Int,
    val name: String,
    val price: Float,
    val description: String?,
    @SerialName("category_id") val categoryId: Int,

    @SerialName(SupabaseTables.PRODUCT_IMAGE) val images: List<ProductImageSerializable>,
)
