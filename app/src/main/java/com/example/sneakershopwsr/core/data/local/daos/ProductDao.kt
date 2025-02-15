package com.example.sneakershopwsr.core.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.sneakershopwsr.core.data.local.entities.ProductEntity
import com.example.sneakershopwsr.core.data.local.entities.ProductWithImagesAndCategoriesAndBaskets


@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(product: List<ProductEntity>)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("SELECT * FROM ProductEntity")
    suspend fun selectProducts(): List<ProductEntity>

    @Transaction
    @Query("SELECT * FROM ProductEntity")
    suspend fun selectProductsWithImagesAndBasket(): List<ProductWithImagesAndCategoriesAndBaskets>
}