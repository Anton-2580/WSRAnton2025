package com.example.sneakershopwsr.onboard.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun OnboardPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    inactiveColor: Color,
    activeColor: Color,
) {
    val space = 2.dp
    val defaultWidth = 30.dp
    val selectedWidth = 50.dp
    val height = 5.dp

    Row {
        repeat(pageCount) {
            val color: Color
            val width: Dp
            if (currentPage == it) {
                color = activeColor
                width = selectedWidth
            } else {
                color = inactiveColor
                width = defaultWidth
            }

            Box(modifier = Modifier
                .height(height)
                .padding(horizontal = space)
                .clip(RoundedCornerShape(5.dp))
                .animateContentSize()
                .background(color)
                .width(width)
            )
        }
    }
}