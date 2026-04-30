package com.makhabatusen.coinrates.feature_coin.presentation.coin_price_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makhabatusen.coinrates.feature_coin.domain.model.CoinPriceHistory
import com.makhabatusen.coinrates.feature_coin.common.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun CoinHistoryListItem(
    coinPriceHistory: CoinPriceHistory,
    modifier: Modifier = Modifier
) {
    val dateFormatted = remember(coinPriceHistory.date) {
        val dateFormatter = SimpleDateFormat(Constants.DATE_FORMAT, Locale.US)
        dateFormatter.format(Date(coinPriceHistory.date))
    }

    val priceFormatted = remember(coinPriceHistory.price) {
        "€ ${String.format(Constants.DOUBLE_VALUE_FORMAT, coinPriceHistory.price)}"
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dateFormatted,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = priceFormatted,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}