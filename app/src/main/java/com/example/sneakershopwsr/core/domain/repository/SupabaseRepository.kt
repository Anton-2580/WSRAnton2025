package com.example.sneakershopwsr.core.domain.repository

import com.example.sneakershopwsr.core.domain.models.ProductCategoryInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImagesAndCategoriesAndBaskets

interface SupabaseRepository {
    suspend fun getProductInfoById(id: Int): ProductInfo
    suspend fun getProductsInfoByName(name: String): List<ProductInfo>
    suspend fun getProductsInfo(count: Long? = null): List<ProductInfo>

    suspend fun getProductsInfoWithImages(count: Long? = null): List<ProductInfoWithImages>
    suspend fun getProductsInfoWithImagesAndBaskets(count: Long? = null): List<ProductInfoWithImagesAndCategoriesAndBaskets>

    suspend fun getCategories(): List<ProductCategoryInfo>
}