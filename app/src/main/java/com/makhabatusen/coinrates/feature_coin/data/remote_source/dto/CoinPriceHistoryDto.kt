package com.makhabatusen.coinrates.feature_coin.data.remote_source.dto


import com.google.gson.annotations.SerializedName
import com.makhabatusen.coinrates.feature_coin.domain.model.CoinPriceHistory

data class CoinPriceHistoryDto(
    @SerializedName("prices")
    val prices: List<List<Double>>,
    @SerializedName("market_caps")
    val marketCaps:  List<List<Double>>,
    @SerializedName("total_volumes")
    val totalVolumes: List<List<Double>>
)

fun CoinPriceHistoryDto.toCoinPriceHistoryList(): List<CoinPriceHistory> {
    val historyPriceData = this.prices
    val coinPriceHistoryList = mutableListOf<CoinPriceHistory>()

    for (data in historyPriceData) {
        if (data.size >= 2) {
            val date = data[0].toLong()
            val price = data[1]
            coinPriceHistoryList.add(CoinPriceHistory(date, price))
        }
    }
    return coinPriceHistoryList
}



