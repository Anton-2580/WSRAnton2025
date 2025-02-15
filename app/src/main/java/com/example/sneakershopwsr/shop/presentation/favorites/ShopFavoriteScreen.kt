package com.example.sneakershopwsr.shop.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons
import com.example.sneakershopwsr.shop.presentation.components.ShopScaffold
import com.example.sneakershopwsr.core.presentation.components.TopActionLine
import com.example.sneakershopwsr.shop.presentation.components.Product
import com.example.sneakershopwsr.ui.theme.BackIcon
import com.example.sneakershopwsr.ui.theme.LikeIcon
import com.example.sneakershopwsr.ui.theme.Text
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.unit.dp


@Composable
fun ShopFavoriteScreen(
    onIconAction: (BottomMenuIcons) -> Unit,
    onLiftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    viewModel: ShopFavoriteViewModel = hiltViewModel(),
) {
    ShopScaffold(
        onIconAction = onIconAction,
        selected = BottomMenuIcons.Favorites,
    ) { spacer ->
            TopActionLine(
                onLiftButtonClick = onLiftButtonClick,
                onRightButtonClick = onRightButtonClick,
                leftIcon = BackIcon,
                rightIcon = LikeIcon,
            ) {
                Text(
                    text = stringResource(R.string.favorite),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Text,
                )
            }

        val count = 2
        LazyVerticalGrid(
            columns = GridCells.Fixed(count),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(viewModel.products.value) {
                Product(
                    productInfo = it,
                    onClick = { },
                    onAddClick = { },
                    liked = true,
                )
            }

            repeat(count) {
                item { spacer() }
            }
        }
    }
}