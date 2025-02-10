package com.example.sneakershopwsr.shop.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.sneakershopwsr.shop.domain.ProductInfoWithImages
import com.example.sneakershopwsr.ui.theme.AccentButtonColors
import com.example.sneakershopwsr.ui.theme.AddIcon
import com.example.sneakershopwsr.ui.theme.BackgroundButtonIconColors
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.LikeIcon


@Composable
fun Product(
    productInfo: ProductInfoWithImages,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(5.dp),
        color = Block,
    ) {
        Column {
            Box {
                IconButton(
                    colors = BackgroundButtonIconColors,
                    onClick = onClick,
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = LikeIcon,
                        contentDescription = "",
                    )
                }

                SubcomposeAsyncImage(
                    model = productInfo.images[0],
//                    model = "https://cdn.sstatic.net/Img/teams/teams-promo.svg?v=e507948b81bf",
                    contentDescription = "",
                    loading = { CircularProgressIndicator() },
                    onError = { Log.e("abra", productInfo.images.toString()) }
                )


            }

            if (productInfo.description !== null)
                Text(text = productInfo.description)
            Text(text = productInfo.name)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "₽ ${productInfo.price}")

                Button(
                    onClick = {},
                    colors = AccentButtonColors,
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(
                        topStart = 75f,
                        bottomEnd = 75f,
                    ),
                    modifier = Modifier.defaultMinSize(50.dp),
                ) {
                    Icon(
                        imageVector = AddIcon,
                        contentDescription = "",
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ProductPreview() {
    Product(
        productInfo = ProductInfoWithImages(
            id = 0,
            name = "Nike",
            price = 100f,
            description = "",
            categoryId = 0,
            images = listOf(),
        )
    ) {}
}