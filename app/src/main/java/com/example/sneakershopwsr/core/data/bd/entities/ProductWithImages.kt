package com.example.sneakershopwsr.core.data.bd.entities

import androidx.room.Embedded
import androidx.room.Relation


class ProductWithImages(
    @Embedded val productInfo: ProductEntity,

    @Relation(
        entity = ProductImageEntity::class,
        parentColumn = "id",
        entityColumn = "productId",
    )
    val images: List<ProductImageEntity>,
)