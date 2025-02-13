package com.example.sneakershopwsr.core.data.repository

import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.core.domain.repository.DatabaseRepository
import com.example.sneakershopwsr.core.domain.repository.FullStackDataInteractor
import com.example.sneakershopwsr.core.domain.repository.SupabaseRepository


class FullStackDataInteractorImpl(
    private val supabaseRepository: SupabaseRepository,
    private val databaseRepository: DatabaseRepository,
): FullStackDataInteractor {

    override suspend fun getProducts(setter: (List<ProductInfoWithImages>) -> Unit) {
        setter(databaseRepository.getProductsInfoWithImagesFromBasket().map { it.toProductInfoWithImages() })

        val products = supabaseRepository.getProductsInfoWithImagesAndBaskets()
        databaseRepository.insertProductsInfoWithImagesFromBasket(products)
        setter(products.map{ it.toProductInfoWithImages() })
    }

}
