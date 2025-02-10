package com.example.sneakershopwsr.shop.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sneakershopwsr.ui.theme.BackgroundButtonIconColors
import com.example.sneakershopwsr.ui.theme.FillLikeIcon
import com.example.sneakershopwsr.ui.theme.LikeIcon


@Composable
fun ProductItem(
    liked: Boolean,
    modifier: Modifier = Modifier,
) {

    Box(modifier = modifier) {
        IconButton(
            onClick = {},
            colors = BackgroundButtonIconColors,
        ) {
            Icon(
                imageVector = if (liked) FillLikeIcon else LikeIcon,
                contentDescription = "",
            )
        }

    }
}


@Preview
@Composable
fun ProductItemProduct() {
    ProductItem(liked = false)
}