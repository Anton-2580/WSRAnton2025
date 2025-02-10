package com.example.sneakershopwsr.shop.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.shop.components.BottomMenu
import com.example.sneakershopwsr.shop.components.BottomMenuIcons
import com.example.sneakershopwsr.shop.presentation.components.Product
import com.example.sneakershopwsr.ui.theme.Text
import com.example.sneakershopwsr.ui.theme.MenuIcon


@Composable
fun ShopHome(
    onAction: (BottomMenuIcons) -> Unit,
    onTopBasketClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        bottomBar = { BottomMenu(
            onAction = onAction,
            selected = BottomMenuIcons.Home,
        ) },
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = MenuIcon,
                        contentDescription = null,
                    )

                    Text(
                        text = stringResource(R.string.home),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Text,
                    )

                    Box {

                    }
                }
            }

            items(viewModel.products.value) {
                Product(productInfo = it) {
                }
            }

        }
    }
}