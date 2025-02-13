package com.example.sneakershopwsr.core.data.network.models

import com.example.sneakershopwsr.core.domain.models.BasketInfo
import com.example.sneakershopwsr.core.domain.models.ProductCategoryInfo
import com.example.sneakershopwsr.core.domain.models.ProductImageInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImagesAndCategoriesAndBaskets


//fun ProductInfo.toProductInfoSerializable() = ProductInfoSerializable(
//    id = id,
//    name = name,
//    price = price,
//    description = description,
//    categoryId = categoryId,
//)

fun ProductInfoSerializable.toProductInfo() = ProductInfo(
    id = id,
    name = name,
    price = price,
    description = description,
    categoryId = categoryId,
)


fun ProductInfoWithImagesSerializable.toProductInfoWithImage() = ProductInfoWithImages(
    id = id,
    name = name,
    price = price,
    description = description,
    categoryId = categoryId,
    images = images.map { it.toProductImage() },
)


fun ProductInfoWithImagesAndCategoriesAndBasketsSerializable.toProductInfoWithImagesAndBaskets() = ProductInfoWithImagesAndCategoriesAndBaskets(
    id = id,
    name = name,
    price = price,
    description = description,
    categoryId = categoryId,
    images = images.map { it.toProductImage() },
    category = category.toProductCategory(),
    baskets = baskets.map { it.toBasketInfo() },
)


fun ProductImageSerializable.toProductImage() = ProductImageInfo(
    id = id,
    url = url,
    productId = productId,
)

fun ProductCategorySerializable.toProductCategory() = ProductCategoryInfo(
    id = id,
    name = name,
)


fun BasketSerializable.toBasketInfo() = BasketInfo(
    id = id,
    productId = productId,
)