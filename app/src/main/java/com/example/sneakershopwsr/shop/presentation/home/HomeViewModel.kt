package com.example.sneakershopwsr.shop.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.core.data.network.BaseEvents
import com.example.sneakershopwsr.core.domain.repository.SupabaseRepository
import com.example.sneakershopwsr.shop.presentation.BaseShopViewModel
import com.example.sneakershopwsr.shop.presentation.CategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val supabaseRepository: SupabaseRepository,
): BaseShopViewModel() {
    private val _categoryState = mutableStateOf(CategoryState())
    val categoryState: State<CategoryState> = _categoryState

    init {
        workWithData<Exception> {
            _products.value =
                supabaseRepository.getProductsInfoWithImagesAndBaskets(COUNT_SHOWN)
                    .map { it.toProductInfoWithImages() }

            _categoryState.value = categoryState.value.copy(
                categories = supabaseRepository.getCategories(),
            )
        }
    }

    companion object {
        const val COUNT_SHOWN = 2L
    }
}
