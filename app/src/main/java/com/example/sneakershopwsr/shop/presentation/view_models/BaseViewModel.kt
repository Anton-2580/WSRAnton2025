package com.example.sneakershopwsr.shop.presentation.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages


open class BaseViewModel: ViewModel() {
    protected val _products: MutableState<List<ProductInfoWithImages>> = mutableStateOf(listOf())
    val products: State<List<ProductInfoWithImages>> = _products
}
