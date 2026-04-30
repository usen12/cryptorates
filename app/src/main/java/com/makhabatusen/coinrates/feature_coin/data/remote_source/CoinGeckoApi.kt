package com.makhabatusen.coinrates.feature_coin.data.remote_source

import com.makhabatusen.coinrates.feature_coin.common.Constants
import com.makhabatusen.coinrates.feature_coin.data.remote_source.dto.CoinDto
import com.makhabatusen.coinrates.feature_coin.data.remote_source.dto.CoinPriceHistoryDto
import com.makhabatusen.coinrates.feature_coin.data.remote_source.dto.SupportedCoinItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {


    companion object {
        const val BASE_URL = "https://api.coingecko.com/api/v3/"
    }

    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = Constants.DEFAULT_CURRENCY_EUR,
        @Query("locale") locale: String = Constants.DEFAULT_LANGUAGE
    ): List<CoinDto>

    @GET("coins/{id}/market_chart")
    suspend fun getCoinHistory(
        @Path("id") id: String,
        @Query("vs_currency") currency: String = Constants.DEFAULT_CURRENCY_EUR,
        @Query("days") days: Int = Constants.DEFAULT_HISTORY_DAYS,
        @Query("interval") interval: String = Constants.DAILY_INTERVAL
    ): CoinPriceHistoryDto

    @GET("coins/list")
    suspend fun getSupportedCoinList(): List<SupportedCoinItemDto>

}
