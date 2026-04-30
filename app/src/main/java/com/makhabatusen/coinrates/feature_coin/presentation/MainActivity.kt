package com.makhabatusen.coinrates.feature_coin.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.makhabatusen.coinrates.feature_coin.presentation.ui.screens.CoinListScreen
import com.makhabatusen.coinrates.feature_coin.presentation.ui.screens.CoinPriceHistoryScreen
import com.makhabatusen.coinrates.feature_coin.presentation.ui.screens.Screen
import com.makhabatusen.coinrates.feature_coin.presentation.ui.theme.CoinRatesTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinRatesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {

                        composable(route = Screen.CoinListScreen.route) {
                            CoinListScreen(navController)
                        }

                        composable(route = Screen.CoinPriceHistoryScreen.route + "/{coinId}") {
                            CoinPriceHistoryScreen(navController)
                        }
                    }
                }
            }
        }
    }


}

