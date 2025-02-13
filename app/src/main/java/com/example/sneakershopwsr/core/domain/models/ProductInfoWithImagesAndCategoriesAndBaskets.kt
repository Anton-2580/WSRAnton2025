package com.example.sneakershopwsr.core.domain.models


data class ProductInfoWithImagesAndCategoriesAndBaskets(
    val id: Int?,
    val name: String,
    val price: Float,
    val description: String?,
    val categoryId: Int,

    val images: List<ProductImageInfo>,

    val category: ProductCategoryInfo,
    val baskets: List<BasketInfo>,
) {
    fun toProductInfoWithImages() = ProductInfoWithImages(
        id = id,
        name = name,
        price = price,
        description = description,
        categoryId = categoryId,
        images = images,
    )
}
