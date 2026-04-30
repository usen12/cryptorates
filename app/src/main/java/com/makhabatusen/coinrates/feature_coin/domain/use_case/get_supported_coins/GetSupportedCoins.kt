package com.makhabatusen.coinrates.feature_coin.domain.use_case.get_supported_coins

import com.makhabatusen.coinrates.feature_coin.domain.model.SupportedCoin
import com.makhabatusen.coinrates.feature_coin.domain.repository.CoinsRepository
import javax.inject.Inject

class GetSupportedCoins @Inject constructor(
    private val repository: CoinsRepository
) {

    suspend operator fun invoke(): List<SupportedCoin> {
        return repository.getSupportedCoins()
    }
}
