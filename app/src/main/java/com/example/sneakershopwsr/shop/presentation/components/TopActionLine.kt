package com.example.sneakershopwsr.shop.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
    leftIcon: ImageVector,
    onLiftButtonClick: () -> Unit,
    rightIcon: ImageVector? = null,
    onRightButtonClick: (() -> Unit)? = null,
    colors: IconButtonColors = WhiteButtonIconColors,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(
            onClick = onLiftButtonClick,
            colors = colors,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = leftIcon,
                contentDescription = "",
            )
        }

        Box(modifier = Modifier.align(Alignment.Center)) {
            content()
        }

        if (rightIcon !== null && onRightButtonClick !== null) {
            IconButton(
                onClick = onRightButtonClick,
                colors = colors,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = rightIcon,
                    contentDescription = "",
                )
            }
        }
    }
}
