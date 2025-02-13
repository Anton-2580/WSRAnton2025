package com.example.sneakershopwsr.shop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons
import com.example.sneakershopwsr.shop.domain.ShopHomeActions
import com.example.sneakershopwsr.shop.presentation.components.Product
import com.example.sneakershopwsr.shop.presentation.components.ShopScaffold
import com.example.sneakershopwsr.shop.presentation.view_models.HomeViewModel
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.Text
import com.example.sneakershopwsr.ui.theme.MenuIcon
import com.example.sneakershopwsr.ui.theme.WhiteButtonColors


@Composable
fun ShopHomeScreen(
    onIconAction: (BottomMenuIcons) -> Unit,
    onActions: (ShopHomeActions) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    onSelectCategory: () -> Unit,
) {
    ShopScaffold(
        onIconAction = onIconAction,
        selected = BottomMenuIcons.Home,
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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

            Text(stringResource(R.string.categories))
            LazyRow {
                items(viewModel.categoryState.value.categories) {
                    Button(
                        onClick = {},
                        colors = WhiteButtonColors,
                        shape = RoundedCornerShape(20)
                    ) {
                        Text(it.name)
                    }
                }
            }

            RowBlock(stringResource(R.string.popular)) { onActions(ShopHomeActions.OnMorePopular) }
            LazyRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(viewModel.products.value) {
                    Product(
                        productInfo = it,
                        onClick = {},
                        onAddClick = {},
                    )
                }
            }

            RowBlock(stringResource(R.string.stocks)) { onActions(ShopHomeActions.OnMoreStocks) }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.Red)
            ) {
                Text(text = "Ad")
            }
        }
    }
}


@Composable
fun RowBlock(
    text: String,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text)
        Text(buildAnnotatedString {
            withLink(
                link = LinkAnnotation.Clickable(
                    tag = "",
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = Accent,
                        )
                    ),
                    linkInteractionListener = { onClick() },
                ),
            ) {
                append(stringResource(R.string.more))
            }
        })
    }
}