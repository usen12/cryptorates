package com.makhabatusen.coinrates.feature_coin.data.local_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.makhabatusen.coinrates.feature_coin.data.local_source.entity.CoinEntity

@Database(entities = [CoinEntity::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "exchange_coin_database"
    }

    abstract fun coinDataDao(): CoinDao
}

