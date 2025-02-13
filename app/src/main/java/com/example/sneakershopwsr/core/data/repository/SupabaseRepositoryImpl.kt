package com.example.sneakershopwsr.core.data.repository

import com.example.sneakershopwsr.core.data.network.SupabaseTables
import com.example.sneakershopwsr.core.data.network.models.ProductCategorySerializable
import com.example.sneakershopwsr.core.data.network.models.ProductInfoSerializable
import com.example.sneakershopwsr.core.data.network.models.ProductInfoWithImagesAndCategoriesAndBasketsSerializable
import com.example.sneakershopwsr.core.data.network.models.ProductInfoWithImagesSerializable
import com.example.sneakershopwsr.core.data.network.models.toProductCategory
import com.example.sneakershopwsr.core.data.network.models.toProductInfo
import com.example.sneakershopwsr.core.data.network.models.toProductInfoWithImage
import com.example.sneakershopwsr.core.data.network.models.toProductInfoWithImagesAndBaskets
import com.example.sneakershopwsr.core.domain.models.ProductCategoryInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImagesAndCategoriesAndBaskets
import com.example.sneakershopwsr.core.domain.repository.SupabaseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.PostgrestFilterDSL
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.request.SelectRequestBuilder
import io.github.jan.supabase.postgrest.result.PostgrestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SupabaseRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): SupabaseRepository {
    private suspend fun fromTable(
        table: String,
        columns: Columns = Columns.ALL,
        request: @PostgrestFilterDSL (SelectRequestBuilder.() -> Unit) = {}
    ): PostgrestResult = withContext(dispatcher) {
        supabaseClient.from(table).select(
            columns = columns,
            request = request,
        )
    }

    override suspend fun getProductInfoById(id: Int): ProductInfo =
        fromTable(table = SupabaseTables.PRODUCT) {
            filter {
                eq("id", id)
            }
        }.decodeSingle<ProductInfoSerializable>().toProductInfo()

    override suspend fun getProductsInfoByName(name: String): List<ProductInfo> =
        fromTable(table = SupabaseTables.PRODUCT) { filter {
            like("name", name)
        } }.decodeList<ProductInfoSerializable>().map { it.toProductInfo() }

    override suspend fun getProductsInfo(count: Long?): List<ProductInfo> =
        fromTable(table = SupabaseTables.PRODUCT) {
            if (count !== null) limit(count = count)
        }.decodeList<ProductInfoSerializable>().map { it.toProductInfo() }


    override suspend fun getProductsInfoWithImages(count: Long?): List<ProductInfoWithImages> =
        fromTable(
            table = SupabaseTables.PRODUCT,
            columns = Columns.raw("*, ${SupabaseTables.PRODUCT_IMAGE}(*)"),
            request = { if (count !== null) limit(count = count) }
        )
            .decodeList<ProductInfoWithImagesSerializable>()
            .map { it.toProductInfoWithImage() }


    override suspend fun getProductsInfoWithImagesAndBaskets(count: Long?): List<ProductInfoWithImagesAndCategoriesAndBaskets> =
        fromTable(
            table = SupabaseTables.PRODUCT,
            columns = Columns.raw("*, ${SupabaseTables.PRODUCT_IMAGE}(*), ${SupabaseTables.PRODUCT_CATEGORY}(*), ${SupabaseTables.BASKET}(*)"),
            request = { if (count !== null) limit(count = count) }
        )
            .decodeList<ProductInfoWithImagesAndCategoriesAndBasketsSerializable>()
            .map { it.toProductInfoWithImagesAndBaskets() }


    override suspend fun getCategories(): List<ProductCategoryInfo> =
        fromTable(table = SupabaseTables.PRODUCT_CATEGORY)
            .decodeList<ProductCategorySerializable>().map { it.toProductCategory() }
}

