package com.example.sneakershopwsr.shop.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.sneakershopwsr.core.domain.models.ProductImageInfo
import com.example.sneakershopwsr.core.domain.models.ProductInfoWithImages
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
    onAddClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Block)
            .clickable { onClick() }
    ) {
        IconButton(
            colors = BackgroundButtonIconColors,
            onClick = onClick,
        ) {
            Icon(
                imageVector = LikeIcon,
                contentDescription = "",
            )
        }

        Column {
            SubcomposeAsyncImage(
                model = productInfo.images[0].url,
                contentDescription = "",
                loading = { CircularProgressIndicator() },
                onError = { Log.e("abra", productInfo.images.toString()) }
            )

            Text(text = productInfo.name)

            Text(text = "₽ ${productInfo.price}")
        }

        Button(
            onClick = onAddClick,
            colors = AccentButtonColors,
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(
                topStart = 50f,
                bottomEnd = 50f,
            ),
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.BottomEnd),
        ) {
            Icon(
                imageVector = AddIcon,
                contentDescription = "",
            )
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
            images = listOf(
                ProductImageInfo(
                    1,
                    "https://eqlghtbaclnlalqtwbrp.supabase.co/storage/v1/object/public/sneaker_images/Nike%20Air%20Max.png",
                    1,
                )
            )
        ),
        onClick = {},
        onAddClick = {},
    )
}