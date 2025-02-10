package com.example.sneakershopwsr.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.ui.graphics.Color


val WhiteButtonColors = ButtonColors(
    containerColor = Block,
    contentColor = Text,
    disabledContainerColor = Color(240f, 240f, 240f),
    disabledContentColor = Text
)

val BackgroundButtonColors = ButtonColors(
    containerColor = Background,
    contentColor = Text,
    disabledContainerColor = Background.copy(alpha = 0.8f),
    disabledContentColor = Text
)
val BackgroundButtonIconColors = BackgroundButtonColors.toIconButtonColors()

val BasketButtonColors = ButtonColors(
    containerColor = Accent,
    contentColor = Block,
    disabledContainerColor = Color(62, 168, 221),
    disabledContentColor = Block
)
val BasketButtonIconColors = BasketButtonColors.toIconButtonColors()


fun ButtonColors.toIconButtonColors() = IconButtonColors(
    containerColor = containerColor,
    contentColor = contentColor,
    disabledContainerColor = disabledContainerColor,
    disabledContentColor = disabledContentColor
)