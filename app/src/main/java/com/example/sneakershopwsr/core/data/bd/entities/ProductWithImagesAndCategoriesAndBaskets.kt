package com.example.sneakershopwsr.core.data.bd.entities

import androidx.room.Embedded
import androidx.room.Relation


data class ProductWithImagesAndCategoriesAndBaskets(
    @Embedded val productInfo: ProductEntity,

    @Relation(
        entity = ProductImageEntity::class,
        parentColumn = "id",
        entityColumn = "productId",
    )
    val images: List<ProductImageEntity>,

    @Relation(
        entity = BasketEntity::class,
        parentColumn = "id",
        entityColumn = "productId",
    )
    val baskets: List<BasketEntity>,

    @Relation(
        entity = ProductCategoryEntity::class,
        parentColumn = "categoryId",
        entityColumn = "id",
    )
    val category: ProductCategoryEntity,
)