package com.example.sneakershopwsr.onboard.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.Sneaker


@Composable
fun OnboardWelcome() {
    Text(
        text = stringResource(R.string.welcome),
        color = Block,
        fontWeight = FontWeight.Bold
    )

    Image(
        painter = Sneaker,
        contentDescription = stringResource(R.string.sneaker_content_description),
        modifier = Modifier
            .requiredSize(500.dp)
            .rotate(-20.15f),
    )
}
