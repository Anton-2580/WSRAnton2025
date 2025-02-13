package com.example.sneakershopwsr.core.data.bd.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductCategoryEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
)