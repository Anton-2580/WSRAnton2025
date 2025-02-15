package com.example.sneakershopwsr.shop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.sneakershopwsr.core.presentation.components.BadgeButton
import com.example.sneakershopwsr.shop.domain.BigMenuItems
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.BasketIcon
import com.example.sneakershopwsr.ui.theme.LikeIcon
import com.example.sneakershopwsr.ui.theme.LogoutIcon
import com.example.sneakershopwsr.ui.theme.NotificationIcon
import com.example.sneakershopwsr.ui.theme.OrdersIcon
import com.example.sneakershopwsr.ui.theme.ProfileIcon
import com.example.sneakershopwsr.ui.theme.SettingsIcon


@Composable
fun BigMenu(
    modifier: Modifier = Modifier,
    onActions: (BigMenuItems) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxSize()
            .background(Accent)
            .padding(start = 10.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(100.dp))
            Box {
                SubcomposeAsyncImage(
                    model = "",
                    contentDescription = "",
                    loading = { CircularProgressIndicator() }
                )
                Text(text = "name")
            }
            Spacer(modifier = Modifier.height(100.dp))
        }

        items(BigMenuItems.entries.toTypedArray()) { item ->
            if (item.isStartNewSection)
                HorizontalDivider(modifier = Modifier.padding(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val icon = when (item) {
                    BigMenuItems.Profile -> ProfileIcon
                    BigMenuItems.Basket -> BasketIcon
                    BigMenuItems.Favorite -> LikeIcon
                    BigMenuItems.Orders -> OrdersIcon
                    BigMenuItems.Notify -> NotificationIcon
                    BigMenuItems.Settings -> SettingsIcon
                    BigMenuItems.Logout -> LogoutIcon
                }

                if (item in listOf(
                        BigMenuItems.Notify,
                )) {
                    BadgeButton(
                        icon = icon,
                        onClick = { onActions(item) },
                        color = Color.Transparent,
                    )
                } else {
                    IconButton(
                        onClick = { onActions(item) },
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "",
                        )
                    }
                }

                Text(
                    text = buildAnnotatedString {
                        withLink(
                            link = LinkAnnotation.Clickable(
                                tag = "",
                                styles = null,
                                linkInteractionListener = { onActions(item) },
                            )
                        ) {
                            append(item.name)
                        }
                    }
                )
            }
        }
    }
}
