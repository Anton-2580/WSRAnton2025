package com.example.sneakershopwsr.core.data.repository

import com.example.sneakershopwsr.core.data.bd.ShopDatabase
import com.example.sneakershopwsr.core.data.bd.entities.BasketEntity
import com.example.sneakershopwsr.core.data.bd.entities.ProductCategoryEntity
import com.example.sneakershopwsr.core.data.bd.entities.ProductEntity
import com.example.sneakershopwsr.core.data.bd.entities.ProductImageEntity
import com.example.sneakershopwsr.core.data.bd.entities.toProductInfoWithImagesAndBaskets
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImagesAndCategoriesAndBaskets
import com.example.sneakershopwsr.core.domain.models.toProductCategoryEntity
import com.example.sneakershopwsr.core.domain.repository.DatabaseRepository


class DatabaseRepositoryImpl(
    private val db: ShopDatabase,
): DatabaseRepository {

    override suspend fun insertProductsInfoWithImagesFromBasket(productsWithImages: List<ProductInfoWithImagesAndCategoriesAndBaskets>) {
        productsWithImages.forEach {
            db.daoProductCategory.insertProductCategory(
                it.category.toProductCategoryEntity()
            )

            db.daoProduct.insertProduct(
                ProductEntity(
                    id = it.id,
                    name = it.name,
                    price = it.price,
                    description = it.description,
                    categoryId = it.categoryId,
                )
            )

            db.daoProductImage.insertProductImages(
                it.images.map { productImage ->
                    ProductImageEntity(
                        id = productImage.id,
                        url = productImage.url,
                        productId = productImage.productId
                    )
                }
            )

            db.daoBasket.insertBaskets(
                it.baskets.map { basket ->
                    BasketEntity(
                        id = basket.id,
                        productId = basket.productId
                    )
                }
            )
        }
    }

    override suspend fun getProductsInfoWithImagesFromBasket(): List<ProductInfoWithImagesAndCategoriesAndBaskets> =
        db.daoProduct.selectProductsWithImagesAndBasket().map { it.toProductInfoWithImagesAndBaskets() }

}