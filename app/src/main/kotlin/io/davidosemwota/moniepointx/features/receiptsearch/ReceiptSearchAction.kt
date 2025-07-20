package io.davidosemwota.moniepointx.features.receiptsearch

sealed interface ReceiptSearchAction {

    data object NavigateBack : ReceiptSearchAction

    data class SearchQueryChanged(val query: String) : ReceiptSearchAction
}

fun onAction(
    action: ReceiptSearchAction,
    onNavigateBack: () -> Unit,
    viewModel: ReceiptSearchViewModel,
) {
    when (action) {
        ReceiptSearchAction.NavigateBack -> {
            onNavigateBack()
        }

        is ReceiptSearchAction.SearchQueryChanged -> {
            viewModel.onSearchQueryChanged(action.query)
        }
    }
}