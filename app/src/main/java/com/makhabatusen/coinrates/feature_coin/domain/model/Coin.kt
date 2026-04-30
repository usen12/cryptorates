package com.makhabatusen.coinrates.feature_coin.domain.model

data class Coin(
    val id: String,
    val name: String,
    val currentPrice: Double?,
    val lastUpdated: String,
    val image: String?
)
