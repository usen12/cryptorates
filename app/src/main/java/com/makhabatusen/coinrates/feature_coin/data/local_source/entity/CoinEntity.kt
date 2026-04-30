package com.makhabatusen.coinrates.feature_coin.data.local_source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.makhabatusen.coinrates.feature_coin.domain.model.Coin


@Entity(tableName = "coin_data")
data class CoinEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val currentPrice: Double?,
    val lastUpdated: String,
    val image: String?,
)

fun CoinEntity.toCoin(): Coin = Coin(
    id = id,
    name = name,
    currentPrice = currentPrice,
    lastUpdated = lastUpdated,
    image = image
)





