package com.example.sneakershopwsr.shop.presentation.view_models

import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.core.domain.repository.FullStackDataInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(
    private val fullStackDataInteractor: FullStackDataInteractor,
): BaseViewModel() {
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