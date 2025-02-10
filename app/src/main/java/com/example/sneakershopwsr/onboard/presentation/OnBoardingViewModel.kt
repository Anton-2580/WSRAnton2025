package com.example.sneakershopwsr.onboard.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(): ViewModel() {
    val targetState = mutableStateOf(PagerIndicatorState(step = 0))
    val lastPageNumber = 2

    fun onLeftSwipe() {
        if (targetState.value.step > 0)
            targetState.value = targetState.value.copy(step = targetState.value.step - 1)
    }

    fun onRightSwipe() {
        if (targetState.value.step < lastPageNumber)
            targetState.value = targetState.value.copy(step = targetState.value.step + 1)
    }
}