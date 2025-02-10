package com.example.sneakershopwsr.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.sneakershopwsr.splash.SplashScreen
import kotlinx.serialization.Serializable


@Serializable
object SplashGraph

@Serializable
object SplashScreen


fun <T : Any> NavGraphBuilder.splashGraph(navController: NavHostController, getNextScreen: () -> T) {
    navigation<SplashGraph>(
        startDestination = SplashScreen
    ) {
        composable<SplashScreen> {
            SplashScreen(
                timeMillis = 2000L,
                onGoToNextScreen = {
                    navController.navigate(getNextScreen()) {
                        popUpTo(SplashScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}