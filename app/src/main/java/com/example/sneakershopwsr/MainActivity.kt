package com.example.sneakershopwsr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.sneakershopwsr.core.navigation.NavigationRoot
import com.example.sneakershopwsr.ui.theme.SneakerShopWSRTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SneakerShopWSR)
        super.onCreate(savedInstanceState)

        setContent {
            SneakerShopWSRTheme {
                val navController = rememberNavController()

                NavigationRoot(
                    navController = navController,
                )
            }
        }
    }
}
