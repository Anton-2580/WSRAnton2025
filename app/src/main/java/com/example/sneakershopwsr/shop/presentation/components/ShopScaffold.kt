package com.example.sneakershopwsr.shop.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.example.sneakershopwsr.core.presentation.components.BottomMenu
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons


@Composable
fun ShopScaffold(
    onIconAction: (BottomMenuIcons) -> Unit,
    selected: BottomMenuIcons,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment. Horizontal = Alignment.Start,
    content: @Composable ColumnScope.(spacer: @Composable () -> Unit) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        content = { innerPadding ->
            val bottomMenuHeightDp = remember { mutableStateOf(0.dp) }

            val paddingValues = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding(),
                start = innerPadding.calculateLeftPadding(LocalLayoutDirection.current) + 10.dp,
                end = innerPadding.calculateRightPadding(LocalLayoutDirection.current) + 10.dp,
            )

            val localDensity = LocalDensity.current

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = verticalArrangement,
                    horizontalAlignment = horizontalAlignment,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    content { Spacer(modifier = Modifier.height(bottomMenuHeightDp.value)) }
                }

                BottomMenu(
                    onAction = onIconAction,
                    selected = selected,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .onSizeChanged { with(localDensity) {
                            bottomMenuHeightDp.value = it.height.toDp()
                        } }
                )
            }
        },
    )
}