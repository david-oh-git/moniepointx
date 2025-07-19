package io.davidosemwota.moniepointx.features.shipment

import androidx.annotation.Keep

@Keep
data class ShipmentState(
    val isLoading: Boolean = false,
)