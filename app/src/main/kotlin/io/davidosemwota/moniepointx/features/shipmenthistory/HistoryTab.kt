package io.davidosemwota.moniepointx.features.shipmenthistory

import androidx.annotation.StringRes
import io.davidosemwota.moniepointx.R

sealed class HistoryTab(
    @StringRes open val nameId: Int,
    val status: Status,
    open val shipments: List<Shipment>
) {

    data class All(
        @StringRes override val nameId: Int = R.string.all_tab,
        override val shipments: List<Shipment>,
    ): HistoryTab(
        nameId = nameId,
        status = Status.ALL,
        shipments = shipments,
    )

    data class Completed(
        @StringRes override val nameId: Int = R.string.completed_tab,
        override val shipments: List<Shipment>,
    ): HistoryTab(
        nameId = nameId,
        status = Status.COMPLETED,
        shipments = shipments,
    )

    data class InProgress(
        @StringRes override val nameId: Int = R.string.in_progress_tab,
        override val shipments: List<Shipment>,
    ): HistoryTab(
        nameId = nameId,
        status = Status.IN_PROGRESS,
        shipments = shipments,
    )

    data class Pending(
        @StringRes override val nameId: Int = R.string.pending_tab,
        override val shipments: List<Shipment>,
    ): HistoryTab(
        nameId = nameId,
        status = Status.PENDING,
        shipments = shipments,
    )

    data class Loading(
        @StringRes override val nameId: Int = R.string.loading_tab,
        override val shipments: List<Shipment>,
    ): HistoryTab(
        nameId = nameId,
        status = Status.LOADING,
        shipments = shipments,
    )

    data class Cancelled(
        @StringRes override val nameId: Int = R.string.cancelled_tab,
        override val shipments: List<Shipment>,
    ): HistoryTab(
        nameId = nameId,
        status = Status.CANCELLED,
        shipments = shipments,
    )
}