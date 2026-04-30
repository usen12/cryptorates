package com.makhabatusen.coinrates.feature_coin.presentation.ui.screens

sealed class Screen(val route : String) {

    object CoinListScreen : Screen("coin_list")
    object CoinPriceHistoryScreen : Screen("coin_price_history_screen")

}
