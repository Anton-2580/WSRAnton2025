package com.example.sneakershopwsr.core.domain.models

import com.example.sneakershopwsr.core.data.local.entities.ProductCategoryEntity


fun ProductCategoryInfo.toProductCategoryEntity() = ProductCategoryEntity(
    id = id,
    name = name,
)
