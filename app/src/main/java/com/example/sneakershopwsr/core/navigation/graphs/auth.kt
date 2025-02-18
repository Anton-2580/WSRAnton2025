package com.example.sneakershopwsr.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.sneakershopwsr.auth.presentation.login.AuthLoginScreen
import com.example.sneakershopwsr.auth.presentation.password_recovery.AuthPasswordRecoveryCodeScreen
import com.example.sneakershopwsr.auth.presentation.password_recovery.AuthPasswordRecoveryScreen
import com.example.sneakershopwsr.auth.presentation.profile.ProfileScreen
import com.example.sneakershopwsr.auth.presentation.registration.AuthRegistrationScreen
import kotlinx.serialization.Serializable


@Serializable
object AuthGraph

@Serializable
data object AuthRegistration

@Serializable
data object AuthLogin

@Serializable
data object AuthPasswordRecovery

@Serializable
data class AuthPasswordRecoveryCode(
    val email: String,
)

@Serializable
data object Profile


fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<AuthGraph>(
        startDestination = AuthLogin
    ) {
        val backClick: () -> Unit = { navController.popBackStack() }

        composable<AuthLogin> {
            AuthLoginScreen(
                onSuccessfulLogin = { navController.navigate(ShopHome) },
                onBackButtonClick = backClick,
                onGoToRegister = { navController.navigate(AuthRegistration) },
                onPasswordRecovery = { navController.navigate(AuthPasswordRecovery) },
            )
        }
        composable<AuthRegistration> {
            AuthRegistrationScreen(
                onSuccessfulRegistration = { navController.navigate(ShopHome) },
                onBackButtonClick = backClick,
                onGoToLogin = { navController.navigate(AuthLogin) },
            )
        }

        composable<AuthPasswordRecovery> {
            AuthPasswordRecoveryScreen(
                onBackButtonClick = backClick,
                onGoToNextScreen = { email ->
                    navController.navigate(AuthPasswordRecoveryCode(
                        email = email
                    ))
                },
            )
        }

        composable<AuthPasswordRecoveryCode> {
            AuthPasswordRecoveryCodeScreen(
                onBackButtonClick = backClick,
                onGoToNextScreen = { navController.navigate(ShopHome) },
            )
        }

        composable<Profile> {
            ProfileScreen(
                onBackButtonClick = backClick,
            )
        }
    }
}


