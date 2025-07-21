package io.davidosemwota.moniepointx.features.shipmenthistory

sealed interface ShipmentHistoryAction {

    data object NavigateBack : ShipmentHistoryAction

    data class SelectTab(val tabIndex: Int) : ShipmentHistoryAction
}

fun onAction(
    action: ShipmentHistoryAction,
    onNavigateBack: () -> Unit,
    viewModel: ShipmentHistoryViewModel,
) {
    when (action) {
        ShipmentHistoryAction.NavigateBack -> {
            onNavigateBack()
        }

        is ShipmentHistoryAction.SelectTab -> {
            viewModel.onSelectedTabIndexChanged(action.tabIndex)
        }
    }
}