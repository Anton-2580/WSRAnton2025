package com.example.sneakershopwsr.shop.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sneakershopwsr.core.data.network.BaseEvents
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import com.example.sneakershopwsr.core.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


open class BaseShopViewModel: BaseViewModel() {
    protected val _products: MutableState<List<ProductInfoWithImages>> = mutableStateOf(listOf())
    val products: State<List<ProductInfoWithImages>> = _products
}
