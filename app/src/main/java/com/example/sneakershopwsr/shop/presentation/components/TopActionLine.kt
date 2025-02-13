package com.example.sneakershopwsr.shop.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sneakershopwsr.ui.theme.WhiteButtonIconColors


@Composable
fun TopActionLine(
    onLiftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    leftIcon: ImageVector,
    rightIcon: ImageVector,
    colors: IconButtonColors = WhiteButtonIconColors,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(
            onClick = onLiftButtonClick,
            colors = colors,
        ) {
            Icon(
                imageVector = leftIcon,
                contentDescription = "",
            )
        }

        content()

        IconButton(
            onClick = onRightButtonClick,
            colors = colors,
        ) {
            Icon(
                imageVector = rightIcon,
                contentDescription = "",
            )
        }
    }
}
