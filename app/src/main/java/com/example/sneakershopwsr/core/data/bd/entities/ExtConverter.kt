package com.example.sneakershopwsr.core.data.bd.entities

import com.example.sneakershopwsr.core.domain.models.BasketInfo
import com.example.sneakershopwsr.core.domain.models.ProductCategoryInfo
import com.example.sneakershopwsr.core.domain.models.ProductImageInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImagesAndCategoriesAndBaskets


fun ProductWithImages.toProductInfoWithImages() = ProductInfoWithImages(
    id = productInfo.id,
    name = productInfo.name,
    price = productInfo.price,
    description = productInfo.description,
    categoryId = productInfo.categoryId,
    images = images.map { it.toProductImageInfo() },
)

fun ProductWithImagesAndCategoriesAndBaskets.toProductInfoWithImagesAndBaskets() = ProductInfoWithImagesAndCategoriesAndBaskets(
    id = productInfo.id,
    name = productInfo.name,
    price = productInfo.price,
    description = productInfo.description,
    categoryId = productInfo.categoryId,
    images = images.map { it.toProductImageInfo() },
    category = category.toProductCategoryInfo(),
    baskets = baskets.map { it.toBasketInfo() }

)


fun ProductImageEntity.toProductImageInfo() = ProductImageInfo(
    id = id,
    url = url,
    productId = productId,
)

fun ProductCategoryEntity.toProductCategoryInfo() = ProductCategoryInfo(
    id = id,
    name = name,
)

fun BasketEntity.toBasketInfo() = BasketInfo(
    id = id,
    productId = productId,
)