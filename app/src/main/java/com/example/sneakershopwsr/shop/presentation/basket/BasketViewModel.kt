package com.example.sneakershopwsr.shop.presentation.basket

import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.core.domain.repository.FullStackDataInteractor
import com.example.sneakershopwsr.shop.presentation.BaseShopViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(
    private val fullStackDataInteractor: FullStackDataInteractor,
): BaseShopViewModel() {
    init {
        viewModelScope.launch {
            fullStackDataInteractor.getProducts { _products.value = it }
        }
    }

    fun onPlusClick(productId: Int) {
    }

    fun onMinusClick(productId: Int) {
    }

    fun onDeleteClick(productId: Int) {
    }

}