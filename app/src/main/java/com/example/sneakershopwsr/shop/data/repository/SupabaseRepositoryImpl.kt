package com.example.sneakershopwsr.shop.data.repository

import android.util.Log
import com.example.sneakershopwsr.core.data.SupabaseTables
import com.example.sneakershopwsr.shop.data.models.ProductInfoSerializable
import com.example.sneakershopwsr.shop.data.models.ProductInfoWithImagesSerializable
import com.example.sneakershopwsr.shop.data.models.toProductInfo
import com.example.sneakershopwsr.shop.data.models.toProductInfoWithImage
import com.example.sneakershopwsr.shop.domain.ProductInfo
import com.example.sneakershopwsr.shop.domain.ProductInfoWithImages
import com.example.sneakershopwsr.shop.domain.SupabaseRepository
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
    private suspend fun fromProduct(
        columns: Columns = Columns.ALL,
        request: @PostgrestFilterDSL (SelectRequestBuilder.() -> Unit) = {}
    ): PostgrestResult = withContext(dispatcher) {
        supabaseClient.from(SupabaseTables.Product.name).select(
            columns = columns,
            request = request,
        )
    }

    override suspend fun getProductInfoById(id: Int): ProductInfo =
        fromProduct {
            filter {
                eq("id", id)
            }
        }.decodeSingle<ProductInfoSerializable>().toProductInfo()

    override suspend fun getProductsInfoByName(name: String): List<ProductInfo> =
        fromProduct { filter {
            like("name", name)
        } }.decodeList<ProductInfoSerializable>().map { it.toProductInfo() }

    override suspend fun getProductsInfo(count: Long?): List<ProductInfo> =
        fromProduct {
            if (count !== null) limit(count = count)
        }.decodeList<ProductInfoSerializable>().map { it.toProductInfo() }


    override suspend fun getProductsInfoWithImages(count: Long?): List<ProductInfoWithImages> =
        fromProduct(Columns.raw("*, ${SupabaseTables.ProductImage.name}(*)")) {
           if (count !== null) limit(count = count)
        }.decodeList<ProductInfoWithImagesSerializable>().map { it.toProductInfoWithImage() }

}

