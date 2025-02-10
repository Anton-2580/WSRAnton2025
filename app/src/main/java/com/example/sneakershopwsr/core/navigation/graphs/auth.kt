package com.example.sneakershopwsr.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.sneakershopwsr.auth.presentation.AuthLoginScreen
import com.example.sneakershopwsr.auth.presentation.AuthRegistrationScreen
import kotlinx.serialization.Serializable


@Serializable
object AuthGraph

@Serializable
data object AuthRegistration

@Serializable
data object AuthLogin


fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<AuthGraph>(
        startDestination = AuthLogin
    ) {
        composable<AuthLogin> {
            AuthLoginScreen(
                onSuccessfulLogin = { navController.navigate(ShopHome) },
            )
        }
        composable<AuthRegistration> {
            AuthRegistrationScreen(
                onSuccessfulRegistration = { navController.navigate(ShopHome) },
            )
        }
    }
}


