package com.makhabatusen.coinrates.feature_coin.utils

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.makhabatusen.coinrates.feature_coin.domain.repository.CoinsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class CryptoUpdateWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: CoinsRepository
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            repository.updateCoinsData()
            Log.d("CryptoUpdateWorker", "Coins updated")
            Result.success()
        } catch (e: Exception) {
            Log.e("CryptoUpdateWorker", "Update failed: ${e.message}")
            Result.failure()
        }
    }
}
