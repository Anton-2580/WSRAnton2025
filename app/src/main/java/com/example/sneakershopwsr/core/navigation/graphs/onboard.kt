package com.example.sneakershopwsr.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.sneakershopwsr.onboard.presentation.OnboardingScreen
import kotlinx.serialization.Serializable


@Serializable
object OnboardGraph

@Serializable
data object OnboardWelcome


fun NavGraphBuilder.onboardWelcome(navController: NavHostController) {
    navigation<OnboardGraph>(
        startDestination = OnboardWelcome
    ) {
        composable<OnboardWelcome> {
            OnboardingScreen(
                onGoToNextScreen = {
                    navController.navigate(ShopHome) {
                        popUpTo(OnboardWelcome) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
