package com.makhabatusen.coinrates.feature_coin.presentation.coin_price_history

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makhabatusen.coinrates.feature_coin.common.Constants
import com.makhabatusen.coinrates.feature_coin.domain.model.CoinPriceHistory
import com.makhabatusen.coinrates.feature_coin.domain.use_case.get_coin_history.GetCoinHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinPriceHistoryViewModel @Inject constructor(
    private val getCoinHistoryUseCase: GetCoinHistory,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinPriceHistory = MutableStateFlow<List<CoinPriceHistory>>(emptyList())
    val coinPriceHistory: StateFlow<List<CoinPriceHistory>> = _coinPriceHistory.asStateFlow()

    private val _coinName = MutableStateFlow("")
    val coinName: StateFlow<String> = _coinName.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
                loadCoinHistory(coinId)
            }
        }
    }

    private suspend fun loadCoinHistory(coinId: String) {
        try {
            _coinPriceHistory.value = getCoinHistoryUseCase(coinId)
            _coinName.value = coinId
        } catch (e: Exception) {
            Log.e("CoinHistoryViewModel", "Error: ${e.message}")
        }
    }
}
