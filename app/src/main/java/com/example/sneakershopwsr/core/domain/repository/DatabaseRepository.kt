package com.example.sneakershopwsr.core.domain.repository

import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImagesAndCategoriesAndBaskets

interface DatabaseRepository {
    suspend fun insertProductsInfoWithImagesFromBasket(productsWithImages: List<ProductInfoWithImagesAndCategoriesAndBaskets>)
    suspend fun getProductsInfoWithImagesFromBasket(): List<ProductInfoWithImagesAndCategoriesAndBaskets>
}