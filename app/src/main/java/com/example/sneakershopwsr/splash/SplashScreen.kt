package com.example.sneakershopwsr.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.Disable
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    timeMillis: Long,
    onGoToNextScreen: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        delay(timeMillis)
        onGoToNextScreen()
    }

    val brush = Brush.verticalGradient(
        colors = listOf(Accent, Disable)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(brush = brush)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash_icon),
            contentDescription = ""
        )
    }
}