package io.davidosemwota.moniepointx.features.estimatedamount

import androidx.annotation.Keep

@Keep
data class EstimatedAmountState(
    val isLoading: Boolean = false,
    val amount: Int = 1000,
    val  currency: String = "USD",
)