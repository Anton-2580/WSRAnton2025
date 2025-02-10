package com.example.sneakershopwsr.shop.data.models

import com.example.sneakershopwsr.shop.domain.ProductImage
import com.example.sneakershopwsr.shop.domain.ProductInfo
import com.example.sneakershopwsr.shop.domain.ProductInfoWithImages


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


fun ProductImageSerializable.toProductImage() = ProductImage(
    id = id,
    url = url,
)