package com.example.sneakershopwsr.core.data.bd.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakershopwsr.core.data.bd.entities.ProductImageEntity


@Dao
interface ProductImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductImages(productImage: List<ProductImageEntity>)

    @Delete
    suspend fun deleteProductImage(productImage: ProductImageEntity)

    @Query("SELECT * FROM ProductImageEntity")
    suspend fun selectProductImages(): List<ProductImageEntity>

}