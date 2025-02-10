package com.example.sneakershopwsr.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.sneakershopwsr.core.navigation.Notifications
import com.example.sneakershopwsr.core.navigation.Profile
import com.example.sneakershopwsr.shop.components.BottomMenuIcons
import com.example.sneakershopwsr.shop.presentation.ShopHome
import kotlinx.serialization.Serializable


@Serializable
object ShopGraph

@Serializable
data object ShopHome

@Serializable
data object ShopFavorites

@Serializable
data object ShopBasket


fun NavGraphBuilder.shopGraph(navController: NavHostController) {
    navigation<ShopGraph>(
        startDestination = ShopHome
    ) {
        val onAction = { action: BottomMenuIcons ->
            when (action) {
                BottomMenuIcons.Home -> navController.navigate(ShopHome)
                BottomMenuIcons.Favorites -> navController.navigate(ShopFavorites)
                BottomMenuIcons.Basket -> navController.navigate(ShopBasket)
                BottomMenuIcons.Notifications -> navController.navigate(Notifications)
                BottomMenuIcons.Profile -> navController.navigate(Profile)
            }
        }

        composable<ShopHome> {
            ShopHome(
                onAction = onAction,
                onTopBasketClick = { navController.navigate(ShopBasket) },
            )
        }
    }
}
