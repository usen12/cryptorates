package com.makhabatusen.coinrates.feature_coin.data.remote_source.dto

import com.google.gson.annotations.SerializedName
import com.makhabatusen.coinrates.feature_coin.domain.model.SupportedCoin

data class SupportedCoinItemDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)

fun SupportedCoinItemDto.toSupportedCoin(): SupportedCoin = SupportedCoin(
    id = id,
    name = name,
    symbol = symbol
)