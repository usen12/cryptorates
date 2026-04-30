package com.makhabatusen.coinrates.feature_coin.domain.repository

import com.makhabatusen.coinrates.feature_coin.domain.model.Coin
import com.makhabatusen.coinrates.feature_coin.domain.model.CoinPriceHistory
import com.makhabatusen.coinrates.feature_coin.domain.model.SupportedCoin
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {

    fun getCoinsFromDB(): Flow<List<Coin>>

    suspend fun updateCoinsData()

    suspend fun getCoinPriceHistoryById(coinId: String): List<CoinPriceHistory>

    suspend fun getSupportedCoins(): List<SupportedCoin>

}