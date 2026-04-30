package com.makhabatusen.coinrates.feature_coin.domain.model

data class SupportedCoin(
    val id: String,
    val name: String,
    val symbol: String
) {
    fun matches(query: String): Boolean {
        return id.contains(query, ignoreCase = true) ||
                name.contains(query, ignoreCase = true)
    }
}
