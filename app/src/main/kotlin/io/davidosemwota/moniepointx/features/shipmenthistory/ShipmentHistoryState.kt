package io.davidosemwota.moniepointx.features.shipmenthistory

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.core.designsystem.MoniePointInCompleted
import io.davidosemwota.moniepointx.core.designsystem.MoniePointInLoading
import io.davidosemwota.moniepointx.core.designsystem.MoniePointInPending
import io.davidosemwota.moniepointx.core.designsystem.MoniePointInProgress
import io.davidosemwota.moniepointx.utils.getDate
import io.davidosemwota.moniepointx.utils.getSpecificDate
import java.util.Calendar

@Keep
data class ShipmentHistoryState(
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val shipments: List<Shipment> = listOf(
        Shipment(
            id = 1,
            status = Status.IN_PROGRESS,
            title = "Arriving today!",
            description = "Your delivery, #NEJ827335535 from Atlanta, is arriving today.",
            amount = "1400",
            date = getDate(
                pattern = "MMM dd, yyyy",
            ),
            currency = "USD",
        ),
        Shipment(
            id = 2,
            status = Status.PENDING,
            title = "Arriving today!",
            description = "Your delivery, #NEJ827335535 from Atlanta, is arriving today.",
            amount = "650",
            date = getDate(
                pattern = "MMM dd, yyyy",
                date = getSpecificDate(
                    day = 15,
                    month = Calendar.JULY,
                    year = 2025
                ),
            ),
            currency = "USD",
        ),
        Shipment(
            id = 3,
            status = Status.PENDING,
            title = "Arriving today!",
            description = "Your delivery, #NEJ827335535 from Atlanta, is arriving today.",
            amount = "150",
            date = getDate(
                pattern = "MMM dd, yyyy",
            ),
            currency = "USD",
        ),
        Shipment(
            id = 4,
            status = Status.LOADING,
            title = "Arriving today!",
            description = "Your delivery, #NEJ827335535 from Atlanta, is arriving today.",
            amount = "1400",
            date = getDate(
                pattern = "MMM dd, yyyy",
            ),
            currency = "USD",
        ),
        Shipment(
            id = 4,
            status = Status.CANCELLED,
            title = "Arriving today!",
            description = "Your delivery, #NEJ827335535 from Atlanta, is arriving today.",
            amount = "400",
            date = getDate(
                pattern = "MMM dd, yyyy",
                date = getSpecificDate(
                    day = 27,
                    month = Calendar.JUNE,
                    year = 2025
                ),
            ),
            currency = "USD",
        ),
        Shipment(
            id = 4,
            status = Status.COMPLETED,
            title = "Arriving today!",
            description = "Your delivery, #NEJ827335535 from Atlanta, is arriving today.",
            amount = "1320",
            date = getDate(
                pattern = "MMM dd, yyyy",
                date = getSpecificDate(
                    day = 10,
                    month = Calendar.MAY,
                    year = 2025
                ),
            ),
            currency = "USD",
        ),
        Shipment(
            id = 4,
            status = Status.COMPLETED,
            title = "Arriving today!",
            description = "Your delivery, #NEJ827335535 from Atlanta, is arriving today.",
            amount = "800",
            date = getDate(
                pattern = "MMM dd, yyyy",
                date = getSpecificDate(
                    day = 18,
                    month = Calendar.JULY,
                    year = 2025
                ),
            ),
            currency = "USD",
        ),
    ),
) {

    val tabs: List<HistoryTab> = listOf(
        HistoryTab.All(shipments = shipments),
        HistoryTab.Completed(shipments = shipments.filter { it.status == Status.COMPLETED }),
        HistoryTab.InProgress(shipments = shipments.filter { it.status == Status.IN_PROGRESS }),
        HistoryTab.Pending(shipments = shipments.filter { it.status == Status.PENDING }),
        HistoryTab.Cancelled(shipments = shipments.filter { it.status == Status.CANCELLED }),
        HistoryTab.Loading(shipments = shipments.filter { it.status == Status.LOADING }),
    )

    val currentTab: HistoryTab
        get() = tabs[selectedTabIndex]
}

data class Shipment(
    val id: Int,
    val status: Status,
    val title: String,
    val description: String,
    val amount: String,
    val currency: String,
    val date: String,
)

enum class Status(
    @StringRes val textId: Int,
    val colour: Color,
    @DrawableRes val iconId: Int,
) {
    IN_PROGRESS(
        textId = R.string.in_progress,
        colour = MoniePointInProgress,
        iconId = R.drawable.sync,
    ),
    PENDING(
        textId = R.string.pending,
        colour = MoniePointInPending,
        iconId = R.drawable.history,
    ),
    LOADING(
        textId = R.string.loading,
        colour = MoniePointInLoading,
        iconId = R.drawable.search_activity,
    ),
    CANCELLED(
        textId = R.string.cancelled,
        colour = Color.Red,
        iconId = R.drawable.cancel,
    ),
    COMPLETED(
        textId = R.string.completed,
        colour = MoniePointInCompleted,
        iconId = R.drawable.check
    ),
    ALL(
        textId = R.string.all_tab,
        colour = Color.Transparent,
        iconId = 0,
    )
}