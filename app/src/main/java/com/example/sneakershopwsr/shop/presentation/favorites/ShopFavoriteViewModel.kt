package com.example.sneakershopwsr.shop.presentation.favorites

import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.core.domain.models.ProductImageInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.shop.presentation.BaseShopViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShopFavoriteViewModel @Inject constructor(
): BaseShopViewModel() {
    init {
        viewModelScope.launch {
            val a = ProductInfoWithImages(id = 0, name = "Nike", price = 100f, description = "", categoryId = 0, images = listOf(ProductImageInfo(1, "https://eqlghtbaclnlalqtwbrp.supabase.co/storage/v1/object/public/sneaker_images/Nike%20Air%20Max.png", 1)))

            _products.value = listOf(a, a, a, a, a, a, a)
        }
    }
}