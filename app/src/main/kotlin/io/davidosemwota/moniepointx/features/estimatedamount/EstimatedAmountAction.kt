package io.davidosemwota.moniepointx.features.estimatedamount

sealed interface EstimatedAmountAction {

    data object NavigateBack : EstimatedAmountAction

    data object NavigateToHome : EstimatedAmountAction
}

fun onAction(
    action: EstimatedAmountAction,
    onNavigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: EstimatedAmountViewModel,
) {
    when (action) {
        EstimatedAmountAction.NavigateBack -> {
            onNavigateBack()
        }

        EstimatedAmountAction.NavigateToHome -> {
            navigateToHome()
        }
    }
}