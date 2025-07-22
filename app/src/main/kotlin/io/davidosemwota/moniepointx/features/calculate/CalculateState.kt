package io.davidosemwota.moniepointx.features.calculate

import androidx.annotation.Keep

@Keep
data class CalculateState(
    val isLoading: Boolean = false,
    val senderLocation: String = "",
    val receiverLocation: String = "",
    val weight: String = "",
    val packageOptions: List<String> = listOf(
        "Box", "Envelope", "Crate"
    ),
    val selectedPackageOption: String = "",
    val categories: List<String> = listOf(
        "Documents",
        "Glass",
        "Liquid",
        "Food",
        "Electronic",
        "Product",
        "Others"
    ),
)