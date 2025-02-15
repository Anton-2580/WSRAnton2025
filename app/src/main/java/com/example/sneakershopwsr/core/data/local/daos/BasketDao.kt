package com.example.sneakershopwsr.core.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakershopwsr.core.data.local.entities.BasketEntity


@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBaskets(baskets: List<BasketEntity>)

    @Delete
    suspend fun deleteBasket(basket: BasketEntity)

    @Query("SELECT * FROM BasketEntity")
    suspend fun selectBaskets(): List<BasketEntity>

}