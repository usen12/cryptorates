package com.makhabatusen.coinrates.feature_coin.presentation.coin_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.makhabatusen.coinrates.feature_coin.domain.model.Coin
import com.makhabatusen.coinrates.feature_coin.domain.use_case.get_and_update_coins.GetAndUpdateCoins
import com.makhabatusen.coinrates.feature_coin.common.Constants
import com.makhabatusen.coinrates.feature_coin.utils.CryptoUpdateWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getAndUpdateCoins: GetAndUpdateCoins,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val coinsData: StateFlow<List<Coin>> = getAndUpdateCoins.coinsFromDB.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        scheduleBackgroundUpdates()
        viewModelScope.launch {
            while (true) {
                getAndUpdateCoins()
                delay(Constants.UPDATE_INTERVAL_MILLIS)
            }
        }
    }

    fun scheduleBackgroundUpdates() {
        val workRequest = PeriodicWorkRequestBuilder<CryptoUpdateWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            Constants.COIN_UPDATE_WORK,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
