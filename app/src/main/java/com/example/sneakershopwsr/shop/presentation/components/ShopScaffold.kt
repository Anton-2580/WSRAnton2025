package com.example.sneakershopwsr.shop.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.example.sneakershopwsr.shop.components.BottomMenu
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons


@Composable
fun ShopScaffold(
    onIconAction: (BottomMenuIcons) -> Unit,
    selected: BottomMenuIcons,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        bottomBar = { BottomMenu(
            onAction = onIconAction,
            selected = selected,
        ) },
        modifier = modifier
            .fillMaxSize(),
        content = { innerPadding ->
            val paddingValues = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding(),
                start = innerPadding.calculateLeftPadding(LocalLayoutDirection.current) + 10.dp,
                end = innerPadding.calculateRightPadding(LocalLayoutDirection.current) + 10.dp,
            )
            content(paddingValues)
        },
    )
}