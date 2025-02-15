package com.example.sneakershopwsr.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.AccentButtonIconColors
import com.example.sneakershopwsr.ui.theme.BasketIcon
import com.example.sneakershopwsr.ui.theme.BottomMenuIcon
import com.example.sneakershopwsr.ui.theme.HomeIcon
import com.example.sneakershopwsr.ui.theme.LikeIcon
import com.example.sneakershopwsr.ui.theme.NotificationIcon
import com.example.sneakershopwsr.ui.theme.ProfileIcon


@Composable
fun BottomMenu(
    onAction: (BottomMenuIcons) -> Unit,
    selected: BottomMenuIcons,
    modifier: Modifier = Modifier,
) {
    val painter = rememberVectorPainter(image = BottomMenuIcon)

    Box(modifier = modifier) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = DefaultShadowColor.copy(alpha = 0.3f),
            modifier = Modifier
                .offset(y = (-1).dp)
                .blur(10.dp)
                .fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .paint(
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                )
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val padding = 40.dp
                var color: Color
                var enabled: Boolean
                var icon: ImageVector

                items(BottomMenuIcons.entries.toTypedArray()) {
                    icon = getIcon(it)
                    val disabledColor = Color.Black
                    color = if (it == selected) Accent else disabledColor
                    enabled = (it != selected)

                    when {
                        BottomMenuIcons.Basket == it -> {
                            val size = 70.dp

                            IconButton(
                                onClick = { onAction(it) },
                                colors = AccentButtonIconColors,
                                enabled = enabled,
                                modifier = Modifier
                                    .padding(bottom = padding)
                                    .size(size)
                                    .shadow(
                                        elevation = 40.dp,
                                        shape = CircleShape,
                                        spotColor = AccentButtonIconColors.containerColor
                                    ),
                            ) {
                                Icon(
                                    imageVector = BasketIcon,
                                    contentDescription = "",
                                    tint = disabledColor
                                )
                            }
                        }

                        else -> {
                            IconButton(
                                onClick = { onAction(it) },
                                modifier = Modifier.padding(top = padding),
                                enabled = enabled
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "",
                                    tint = color
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun getIcon(icon: BottomMenuIcons): ImageVector = when (icon) {
    BottomMenuIcons.Home -> HomeIcon
    BottomMenuIcons.Favorites -> LikeIcon
    BottomMenuIcons.Basket -> BasketIcon
    BottomMenuIcons.Notifications -> NotificationIcon
    BottomMenuIcons.Profile -> ProfileIcon
}


@Preview
@Composable
fun BottomMenuPreview() {
    BottomMenu({ }, BottomMenuIcons.Home)
}