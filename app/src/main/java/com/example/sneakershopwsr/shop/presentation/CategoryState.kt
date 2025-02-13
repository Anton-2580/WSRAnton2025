package com.example.sneakershopwsr.shop.presentation

import com.example.sneakershopwsr.core.domain.models.ProductCategoryInfo

data class CategoryState(
    val categories: List<ProductCategoryInfo> = listOf(),
    val selectCategoryId: Int? = null,
)