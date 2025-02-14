package com.example.sneakershopwsr.onboard.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakershopwsr.R
import com.example.sneakershopwsr.onboard.presentation.pages.OnboardStartTheJourney
import com.example.sneakershopwsr.onboard.presentation.pages.OnboardWelcome
import com.example.sneakershopwsr.onboard.presentation.pages.OnboardYouHaveThePower
import com.example.sneakershopwsr.ui.theme.Accent
import com.example.sneakershopwsr.ui.theme.Block
import com.example.sneakershopwsr.ui.theme.Disable
import com.example.sneakershopwsr.ui.theme.Inactive
import com.example.sneakershopwsr.ui.theme.WhiteButtonColors


@Composable
fun OnboardingScreen(
    onGoToNextScreen: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectSwipe(
                    onLeft = { viewModel.onLeftSwipe() },
                    onRight = { viewModel.onRightSwipe() },
                )
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Accent, Disable)
                )
            ),
    ) {
        val text = remember { mutableStateOf("") }
        var onClick by remember { mutableStateOf({}) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            AnimatedContent(
                targetState = viewModel.targetState.value,
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(durationMillis = 300, delayMillis = 150)
                    ) togetherWith fadeOut(
                        animationSpec = tween(durationMillis = 300, delayMillis = 150)
                    )
                },
                label = "",
                modifier = Modifier.weight(0.9f),
            ) { state ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                ) {
                    when (state.step) {
                        0 -> {
                            OnboardWelcome()
                            text.value = stringResource(R.string.begin)
                            onClick = { viewModel.onRightSwipe() }
                        }

                        1 -> {
                            OnboardStartTheJourney()
                            text.value = stringResource(R.string.next)
                            onClick = { viewModel.onRightSwipe() }
                        }

                        2 -> {
                            OnboardYouHaveThePower()
                            text.value = stringResource(R.string.next)
                            onClick = onGoToNextScreen
                        }
                    }
                }
            }

            OnboardPagerIndicator(
                pageCount = viewModel.lastPageNumber + 1,
                currentPage = viewModel.targetState.value.step,
                inactiveColor = Inactive,
                activeColor = Block,
            )

            Box(modifier = Modifier.weight(0.1f)) {
                Button(
                    onClick = onClick,
                    colors = WhiteButtonColors,
                    modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                ) {
                    Text(text = text.value)
                }
            }
        }
    }
}
