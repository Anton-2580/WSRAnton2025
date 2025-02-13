package com.example.sneakershopwsr.core.data.bd.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakershopwsr.core.data.bd.entities.ProductCategoryEntity


@Dao
interface ProductCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCategory(productCategory: ProductCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCategories(productCategories: List<ProductCategoryEntity>)

    @Delete
    suspend fun deleteProductCategory(productCategory: ProductCategoryEntity)

    @Query("SELECT * FROM ProductCategoryEntity")
    suspend fun selectProductCategories(): List<ProductCategoryEntity>

}