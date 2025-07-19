package io.davidosemwota.moniepointx.features.calculate

sealed interface CalculateAction {

    data object NavigateBack : CalculateAction
}

fun onAction(
    action: CalculateAction,
    onNavigateBack: () -> Unit,
    viewModel: CalculateViewModel,
) {
    when (action) {
        CalculateAction.NavigateBack -> {
            onNavigateBack()
        }
    }
}