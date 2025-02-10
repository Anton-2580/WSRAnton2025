package com.example.sneakershopwsr.shop.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.shop.domain.ProductInfoWithImages
import com.example.sneakershopwsr.shop.domain.SupabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val supabaseRepository: SupabaseRepository,
): ViewModel() {
    private val _products: MutableState<List<ProductInfoWithImages>> = mutableStateOf(listOf())
    val products: State<List<ProductInfoWithImages>> = _products

    init {
        getProductsWithImages()
    }

    private fun getProductsWithImages(count: Long? = null) {
        viewModelScope.launch {
//            _products.value = supabaseRepository.getProductsInfoWithImages(count)
            supabaseRepository.getProductsInfo(count)
        }
    }
}
