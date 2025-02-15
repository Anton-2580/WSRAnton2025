package com.example.sneakershopwsr.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sneakershopwsr.core.data.local.daos.BasketDao
import com.example.sneakershopwsr.core.data.local.daos.ProductCategoryDao
import com.example.sneakershopwsr.core.data.local.daos.ProductDao
import com.example.sneakershopwsr.core.data.local.daos.ProductImageDao
import com.example.sneakershopwsr.core.data.local.entities.BasketEntity
import com.example.sneakershopwsr.core.data.local.entities.ProductImageEntity
import com.example.sneakershopwsr.core.data.local.entities.ProductCategoryEntity
import com.example.sneakershopwsr.core.data.local.entities.ProductEntity


@Database(
    version = 1,
    entities = [
        ProductEntity::class,
        ProductCategoryEntity::class,
        ProductImageEntity::class,
        BasketEntity::class,
    ],
)
abstract class ShopDatabase: RoomDatabase() {
    abstract val daoProduct: ProductDao
    abstract val daoProductCategory: ProductCategoryDao
    abstract val daoProductImage: ProductImageDao
    abstract val daoBasket: BasketDao
}