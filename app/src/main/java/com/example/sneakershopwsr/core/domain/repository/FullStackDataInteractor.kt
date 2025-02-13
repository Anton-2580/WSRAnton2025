package com.example.sneakershopwsr.core.domain.repository

import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages


interface FullStackDataInteractor {
    suspend fun getProducts(setter: (List<ProductInfoWithImages>) -> Unit)
}