package com.example.sneakershopwsr.onboard.presentation

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.pointer.PointerInputScope


enum class SwipeState {
    Right,
    Left,
}


suspend fun PointerInputScope.detectSwipe(
    onRight: () -> Unit,
    onLeft: () -> Unit,
    swipeState: MutableState<SwipeState> = mutableStateOf(SwipeState.Right)
) = detectHorizontalDragGestures(
    onHorizontalDrag = { _, x ->
        when {
            x < 0 -> swipeState.value = SwipeState.Right
            x > 0 -> swipeState.value = SwipeState.Left
        }
    },
    onDragEnd = {
        when(swipeState.value) {
            SwipeState.Right -> onRight()
            SwipeState.Left -> onLeft()
        }
    },
)