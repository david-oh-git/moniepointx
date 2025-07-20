package io.davidosemwota.moniepointx.features.receiptsearch

sealed interface ReceiptSearchAction {

    data object NavigateBack : ReceiptSearchAction
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
    }
}