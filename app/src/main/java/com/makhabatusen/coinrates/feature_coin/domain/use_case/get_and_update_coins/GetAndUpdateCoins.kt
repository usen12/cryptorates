package com.makhabatusen.coinrates.feature_coin.domain.use_case.get_and_update_coins

import com.makhabatusen.coinrates.feature_coin.domain.model.Coin
import com.makhabatusen.coinrates.feature_coin.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAndUpdateCoins @Inject constructor(private val repository: CoinsRepository) {

    val coinsFromDB: Flow<List<Coin>> = repository.getCoinsFromDB()

    suspend operator fun invoke() {
        repository.updateCoinsData()
    }
}
