package io.davidosemwota.moniepointx.features.home

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import io.davidosemwota.moniepointx.R

@Keep
data class HomeState(
    val isLoading: Boolean = false,
    val location: String = "Wertheimer, Illinois",
    val receiptNumber: String = "",
    val shipmentNumber: String = "NEJ20089934122231",
    val senderCity: String = "Atlanta, 5243",
    val receiverCity: String = "Chicago, 6342",
    val status: String = "Waiting to collect",
    val vehicles: List<Vehicle> = listOf(
        Vehicle(
            type = "Ocean freight",
            comment = "International",
            iconId = R.drawable.cargo,
        ),
        Vehicle(
            type = "Cargo freight",
            comment = "International",
            iconId = R.drawable.cargo,
        ),
        Vehicle(
            type = "Air freight",
            comment = "International",
            iconId = R.drawable.cargo,
        ),
    )
)

data class Vehicle(
    val type: String,
    val comment: String,
    @DrawableRes val iconId: Int,
)