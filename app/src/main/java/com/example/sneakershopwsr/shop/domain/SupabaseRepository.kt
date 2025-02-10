package com.example.sneakershopwsr.shop.domain

interface SupabaseRepository {
    suspend fun getProductInfoById(id: Int): ProductInfo
    suspend fun getProductsInfoByName(name: String): List<ProductInfo>
    suspend fun getProductsInfo(count: Long? = null): List<ProductInfo>

    suspend fun getProductsInfoWithImages(count: Long?): List<ProductInfoWithImages>
}