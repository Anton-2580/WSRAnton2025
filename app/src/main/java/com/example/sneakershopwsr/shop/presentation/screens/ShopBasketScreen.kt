package com.example.sneakershopwsr.shop.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons
import com.example.sneakershopwsr.shop.domain.SwipeableProductActions
import com.example.sneakershopwsr.shop.presentation.components.ShopScaffold
import com.example.sneakershopwsr.shop.presentation.components.SwipeableProduct
import com.example.sneakershopwsr.core.presentation.components.TopActionLine
import com.example.sneakershopwsr.shop.presentation.view_models.BasketViewModel
import com.example.sneakershopwsr.ui.theme.BackIcon
import com.example.sneakershopwsr.ui.theme.Text


@Composable
fun ShopBasketScreen(
    onIconAction: (BottomMenuIcons) -> Unit,
    onLiftButtonClick: () -> Unit,
    viewModel: BasketViewModel = hiltViewModel(),
) {
    ShopScaffold(
        onIconAction = onIconAction,
        selected = BottomMenuIcons.Favorites,
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                TopActionLine(
                    onLiftButtonClick = onLiftButtonClick,
                    leftIcon = BackIcon,
                ) {
                    Text(
                        text = stringResource(R.string.favorite),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Text,
                    )
                }

                Text(text = viewModel.products.value.size.toString() + stringResource(R.string.products_in_basket))
            }

            items(viewModel.products.value) {
                SwipeableProduct(
                    product = it,
                    onActions = { action ->
                        if (it.id !== null) {
                            when (action) {
                                SwipeableProductActions.OnPlusClick -> viewModel.onPlusClick(it.id)
                                SwipeableProductActions.OnMinusClick -> viewModel.onMinusClick(it.id)
                                SwipeableProductActions.OnDeleteClick -> viewModel.onDeleteClick(it.id)
                            }
                        }
                    },
                )
            }
        }
    }

}