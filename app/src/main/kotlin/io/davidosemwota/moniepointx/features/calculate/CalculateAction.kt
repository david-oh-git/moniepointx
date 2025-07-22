package io.davidosemwota.moniepointx.features.calculate

sealed interface CalculateAction {

    data object NavigateBack : CalculateAction

    data object NavigateToEstimatedAmount : CalculateAction

    data class PackageOptionSelected(val option: String) : CalculateAction

    data class SenderLocationChanged(val location: String) : CalculateAction

    data class ReceiverLocationChanged(val location: String) : CalculateAction

    data class WeightChanged(val weight: String) : CalculateAction
}

fun onAction(
    action: CalculateAction,
    onNavigateBack: () -> Unit,
    navigateToEstimatedAmount: () -> Unit,
    viewModel: CalculateViewModel,
) {
    when (action) {
        CalculateAction.NavigateBack -> {
            onNavigateBack()
        }
        CalculateAction.NavigateToEstimatedAmount -> {
            navigateToEstimatedAmount()
        }

        is CalculateAction.PackageOptionSelected -> {
            viewModel.onSelectedPackageOptionChanged(action.option)
        }
        is CalculateAction.WeightChanged -> {
            viewModel.onPackageWeightChanged(action.weight)
        }
        is CalculateAction.SenderLocationChanged -> {
            viewModel.onSenderLocationChanged(action.location)
        }
        is CalculateAction.ReceiverLocationChanged -> {
            viewModel.onReceiverLocationChanged(action.location)
        }
    }
}