package io.davidosemwota.moniepointx.features.home

sealed interface HomeAction {

    data object NavigateBack : HomeAction
    data object NavigateToReceiptSearch : HomeAction

    data class OnReceiptNumberChange(val receiptNumber: String) : HomeAction
}

fun onAction(
    action: HomeAction,
    onNavigateBack: () -> Unit,
    navigateToReceiptSearch: () -> Unit,
    viewModel: HomeViewModel,
) {
    when (action) {
        HomeAction.NavigateBack -> {
            onNavigateBack()
        }
        HomeAction.NavigateToReceiptSearch -> {
            navigateToReceiptSearch()
        }

        is HomeAction.OnReceiptNumberChange -> {
            viewModel.onReceiptNumberChanged(action.receiptNumber)
        }

    }
}