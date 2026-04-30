 package com.makhabatusen.coinrates.feature_coin.data.remote_source.dto

import com.google.gson.annotations.SerializedName
import com.makhabatusen.coinrates.feature_coin.data.local_source.entity.CoinEntity
import java.math.BigDecimal


data class CoinDto(

    val ath: BigDecimal,
    @SerializedName("ath_change_percentage")
    val athChangePercentage: BigDecimal,
    @SerializedName("ath_date")
    val athDate: String,
    val atl: BigDecimal,
    @SerializedName("atl_change_percentage")
    val atlChangePercentage: BigDecimal,
    @SerializedName("atl_date")
    val atlDate: String,
    @SerializedName("circulating_supply")
    val circulatingSupply: BigDecimal,
    @SerializedName("current_price")
    val currentPrice: BigDecimal,
    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Long,
    @SerializedName("high_24h")
    val high24h: BigDecimal,
    val id: String,
    val image: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("low_24h")
    val low24h: BigDecimal,
    @SerializedName("market_cap")
    val marketCap: BigDecimal,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: BigDecimal,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: BigDecimal,
    @SerializedName("market_cap_rank")
    val marketCapRank: BigDecimal,
    @SerializedName("max_supply")
    val maxSupply: BigDecimal,
    val name: String,
    @SerializedName("price_change_24h")
    val priceChange24h: BigDecimal,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: BigDecimal,
    val roi: Any?,
    val symbol: String,
    @SerializedName("total_supply")
    val totalSupply: BigDecimal,
    @SerializedName("total_volume")
    val totalVolume: BigDecimal
)


fun CoinDto.toCoinEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        name = name,
        currentPrice = currentPrice.toDouble(),
        lastUpdated = lastUpdated,
        image = image
    )
}