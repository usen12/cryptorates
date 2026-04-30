package com.makhabatusen.coinrates.feature_coin.domain.use_case.get_coin_history

import com.makhabatusen.coinrates.feature_coin.domain.model.CoinPriceHistory
import com.makhabatusen.coinrates.feature_coin.domain.repository.CoinsRepository
import javax.inject.Inject

class GetCoinHistory @Inject constructor(
    private val repository: CoinsRepository
) {

    suspend operator fun invoke(coinId: String): List<CoinPriceHistory> {
        return repository.getCoinPriceHistoryById(coinId)
    }
}
