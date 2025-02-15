package com.example.sneakershopwsr.shop.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sneakershopwsr.core.data.network.NetworkEvents
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


open class BaseViewModel: ViewModel() {
    protected val _products: MutableState<List<ProductInfoWithImages>> = mutableStateOf(listOf())
    val products: State<List<ProductInfoWithImages>> = _products

    protected val _eventFlow = MutableSharedFlow<NetworkEvents>()
    val eventFlow: SharedFlow<NetworkEvents> = _eventFlow
}
