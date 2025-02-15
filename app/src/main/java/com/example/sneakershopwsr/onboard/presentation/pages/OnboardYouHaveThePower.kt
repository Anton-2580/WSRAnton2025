package com.example.sneakershopwsr.onboard.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.GrayD8
import com.example.sneakershopwsr.ui.theme.Nike


@Composable
fun OnboardYouHaveThePower() {
    Image(
        painter = Nike,
        contentDescription = stringResource(R.string.nike_content_description),
        modifier = Modifier
            .rotate(160f)
            .requiredSize(500.dp)
            .scale(1f, -1f),
    )

    Text(
        text = stringResource(R.string.you_have_the_power_to),
        color = Block,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = stringResource(R.string.you_have_the_power_to_description),
        color = GrayD8,
        textAlign = TextAlign.Center,
    )
}
