package com.example.sneakershopwsr.core.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sneakershopwsr.MainViewModel
import com.example.sneakershopwsr.core.navigation.graphs.OnboardWelcome
import com.example.sneakershopwsr.core.navigation.graphs.ShopHome
import com.example.sneakershopwsr.core.navigation.graphs.SplashGraph
import com.example.sneakershopwsr.core.navigation.graphs.authGraph
import com.example.sneakershopwsr.core.navigation.graphs.onboardWelcome
import com.example.sneakershopwsr.core.navigation.graphs.shopGraph
import com.example.sneakershopwsr.core.navigation.graphs.splashGraph
import kotlinx.serialization.Serializable

import com.example.sneakershopwsr.core.navigation.graphs.AuthGraph

@Composable
fun NavigationRoot(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val showSplashScreen = Build.VERSION.SDK_INT >= 31
    val getNextScreen: () -> Any = {
        if (mainViewModel.state.isFirstEntrance) OnboardWelcome
        else ShopHome
    }

    NavHost(
        navController = navController,
//        startDestination = if (showSplashScreen) SplashGraph else getNextScreen(),
        startDestination = AuthGraph,
    ) {
        splashGraph(
            navController = navController,
            getNextScreen = { getNextScreen() },
        )
        authGraph(navController)
        shopGraph(navController)
        onboardWelcome(navController)
    }
}


@Serializable
data object Notifications

