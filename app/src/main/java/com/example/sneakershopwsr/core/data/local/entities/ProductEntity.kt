package com.example.sneakershopwsr.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ProductCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class ProductEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val price: Float,
    val description: String?,
    val categoryId: Int,
)
