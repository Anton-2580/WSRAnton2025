package com.example.sneakershopwsr.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopwsr.core.data.network.BaseEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass


open class BaseViewModel: ViewModel() {
    protected val _eventFlow = MutableSharedFlow<BaseEvents>()
    val eventFlow: SharedFlow<BaseEvents> = _eventFlow

    protected fun <T: Throwable> workWithData(
        error: KClass<T>,
        content: suspend () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                _eventFlow.emit(BaseEvents.Loading)
                content()
                _eventFlow.emit(BaseEvents.Successful)
            } catch (e: Throwable) {
                if (!error.isInstance(e)) {
                    throw e
                }

                _eventFlow.emit(BaseEvents.Error(e.message.toString()))
            }
        }
    }

    protected inline fun <reified T: Throwable> workWithData(
        noinline content: suspend () -> Unit,
    ) {
        workWithData(
            T::class,
            content = content
        )
    }
}