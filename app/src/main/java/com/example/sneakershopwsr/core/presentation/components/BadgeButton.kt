package com.example.sneakershopwsr.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun BadgeButton(
    icon: ImageVector,
    color: Color,
    showBadge: Boolean = true,
    contentDescription: String? = "",
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    shape: RoundedCornerShape = CircleShape,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .clickable { onClick() }
            .background(color)
    ) {
        BadgedBox(
            badge = { if (showBadge) Badge() },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
        }
    }
}
