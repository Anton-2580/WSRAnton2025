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
import androidx.compose.ui.unit.dp
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.GrayD8
import com.example.sneakershopwsr.ui.theme.Spring_prev


@Composable
fun OnboardStartTheJourney() {
    Image(
        painter = Spring_prev,
        contentDescription = stringResource(R.string.spring_prev_content_description),
        modifier = Modifier
            .rotate(160f)
            .requiredSize(500.dp)
            .scale(1f, -1f),
    )

    Text(
        text = stringResource(R.string.lets_start_journey),
        color = Block,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = stringResource(R.string.lets_start_journey_description),
        color = GrayD8,
    )
}