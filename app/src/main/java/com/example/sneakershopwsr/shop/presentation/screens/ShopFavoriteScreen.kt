package com.example.sneakershopwsr.shop.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons
import com.example.sneakershopwsr.shop.presentation.components.ShopScaffold
import com.example.sneakershopwsr.shop.presentation.components.TopActionLine
import com.example.sneakershopwsr.ui.theme.BackIcon
import com.example.sneakershopwsr.ui.theme.LikeIcon
import com.example.sneakershopwsr.ui.theme.Text


@Composable
fun ShopFavoriteScreen(
    onIconAction: (BottomMenuIcons) -> Unit,
) {
    ShopScaffold(
        onIconAction = onIconAction,
        selected = BottomMenuIcons.Favorites,
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            TopActionLine(
                onLiftButtonClick = {},
                onRightButtonClick = {},
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

        }
    }
}