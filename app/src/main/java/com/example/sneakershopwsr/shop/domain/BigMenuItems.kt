package com.example.sneakershopwsr.shop.domain


enum class BigMenuItems(
    val isStartNewSection: Boolean = false,
) {
    Profile,
    Basket,
    Favorite,
    Orders,
    Notify,
    Settings,
    Logout(isStartNewSection = true),
}