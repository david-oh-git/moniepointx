package io.davidosemwota.moniepointx.features.shipmenthistory

import androidx.annotation.StringRes
import io.davidosemwota.moniepointx.R

sealed class HistoryTab(
    @StringRes open val nameId: Int,
    val status: Status,
) {

    data class All(
        @StringRes override val nameId: Int = R.string.all_tab,
    ): HistoryTab(
        nameId = nameId,
        status = Status.ALL,
    )

    data class Completed(
        @StringRes override val nameId: Int = R.string.completed_tab,
    ): HistoryTab(
        nameId = nameId,
        status = Status.COMPLETED,
    )

    data class InProgress(
        @StringRes override val nameId: Int = R.string.in_progress_tab,
    ): HistoryTab(
        nameId = nameId,
        status = Status.IN_PROGRESS,
    )

    data class Pending(
        @StringRes override val nameId: Int = R.string.pending_tab,
    ): HistoryTab(
        nameId = nameId,
        status = Status.PENDING,
    )

    data class Loading(
        @StringRes override val nameId: Int = R.string.loading_tab,
    ): HistoryTab(
        nameId = nameId,
        status = Status.LOADING,
    )

    data class Cancelled(
        @StringRes override val nameId: Int = R.string.cancelled_tab,
    ): HistoryTab(
        nameId = nameId,
        status = Status.CANCELLED,
    )
}