package io.davidosemwota.moniepointx.features.shipment

sealed interface ShipmentAction {

    data object NavigateBack : ShipmentAction
}

fun onAction(
    action: ShipmentAction,
    onNavigateBack: () -> Unit,
    viewModel: ShipmentViewModel,
) {
    when (action) {
        ShipmentAction.NavigateBack -> {
            onNavigateBack()
        }
    }
}