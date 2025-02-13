package com.example.sneakershopwsr.core.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sneakershopwsr.core.data.bd.daos.BasketDao
import com.example.sneakershopwsr.core.data.bd.daos.ProductCategoryDao
import com.example.sneakershopwsr.core.data.bd.daos.ProductDao
import com.example.sneakershopwsr.core.data.bd.daos.ProductImageDao
import com.example.sneakershopwsr.core.data.bd.entities.BasketEntity
import com.example.sneakershopwsr.core.data.bd.entities.ProductImageEntity
import com.example.sneakershopwsr.core.data.bd.entities.ProductCategoryEntity
import com.example.sneakershopwsr.core.data.bd.entities.ProductEntity


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