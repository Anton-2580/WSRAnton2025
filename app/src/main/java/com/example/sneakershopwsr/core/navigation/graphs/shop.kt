package com.example.sneakershopwsr.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.sneakershopwsr.core.navigation.Notifications
import com.example.sneakershopwsr.core.navigation.Profile
import com.example.sneakershopwsr.shop.domain.BottomMenuIcons
import com.example.sneakershopwsr.shop.presentation.screens.ShopHomeScreen
import com.example.sneakershopwsr.shop.domain.ShopHomeActions
import com.example.sneakershopwsr.shop.presentation.screens.ShopBasketScreen
import com.example.sneakershopwsr.shop.presentation.screens.ShopFavoriteScreen
import kotlinx.serialization.Serializable


@Serializable
object ShopGraph

@Serializable
data object ShopHome

@Serializable
data object ShopFavorites

@Serializable
data object ShopBasket


@Serializable
data object OutDoor

@Serializable
data object Popular

@Serializable
data object Stocks


fun NavGraphBuilder.shopGraph(navController: NavHostController) {
    navigation<ShopGraph>(
        startDestination = ShopHome
    ) {
        val onIconAction = { action: BottomMenuIcons ->
            when (action) {
                BottomMenuIcons.Home -> navController.navigate(ShopHome)
                BottomMenuIcons.Favorites -> navController.navigate(ShopFavorites)
                BottomMenuIcons.Basket -> navController.navigate(ShopBasket)
                BottomMenuIcons.Notifications -> navController.navigate(Notifications)
                BottomMenuIcons.Profile -> navController.navigate(Profile)
            }
        }

        composable<ShopHome> {
            ShopHomeScreen(
                onIconAction = onIconAction,
                onActions = {
                    when (it) {
                        ShopHomeActions.OnTopBasketClick -> navController.navigate(ShopBasket)
                        ShopHomeActions.OnMorePopular -> navController.navigate(Popular)
                        ShopHomeActions.OnMoreStocks -> navController.navigate(Stocks)
                    }
                },
                onSelectCategory = { navController.navigate(OutDoor) },
            )
        }

        composable<ShopFavorites> {
            ShopFavoriteScreen(
                onIconAction = onIconAction,
            )
        }

        composable<ShopBasket> {
            ShopBasketScreen()
        }
    }
}
