package com.makhabatusen.coinrates.feature_coin.data.repository

import android.util.Log
import com.makhabatusen.coinrates.feature_coin.data.remote_source.CoinGeckoApi
import com.makhabatusen.coinrates.feature_coin.data.local_source.CoinDatabase
import com.makhabatusen.coinrates.feature_coin.data.local_source.entity.toCoin
import com.makhabatusen.coinrates.feature_coin.data.remote_source.dto.toCoinPriceHistoryList
import com.makhabatusen.coinrates.feature_coin.data.remote_source.dto.toSupportedCoin
import com.makhabatusen.coinrates.feature_coin.data.remote_source.dto.toCoinEntity
import com.makhabatusen.coinrates.feature_coin.domain.model.Coin
import com.makhabatusen.coinrates.feature_coin.domain.model.CoinPriceHistory
import com.makhabatusen.coinrates.feature_coin.domain.model.SupportedCoin
import com.makhabatusen.coinrates.feature_coin.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoinsRepositoryImpl(
    private val api: CoinGeckoApi,
    private val db: CoinDatabase
) : CoinsRepository {

    override fun getCoinsFromDB(): Flow<List<Coin>> {
        return db.coinDataDao().getCoins().map { entities -> entities.map { it.toCoin() } }
    }

    override suspend fun updateCoinsData() {
        try {
            val coinEntities = api.getCoins().map { it.toCoinEntity() }
            db.coinDataDao().insertAll(coinEntities)
        } catch (e: Exception) {
            Log.e("CoinsRepositoryImpl", "Exception: ${e.message}")
        }
    }

    override suspend fun getCoinPriceHistoryById(coinId: String): List<CoinPriceHistory> {
        return api.getCoinHistory(coinId).toCoinPriceHistoryList()
    }

    override suspend fun getSupportedCoins(): List<SupportedCoin> {
        return api.getSupportedCoinList().map { it.toSupportedCoin() }
    }
}
