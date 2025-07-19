package io.davidosemwota.moniepointx.features.home

sealed interface HomeAction {

    data object NavigateBack : HomeAction
}

fun onAction(
    action: HomeAction,
    onNavigateBack: () -> Unit,
    viewModel: HomeViewModel,
) {
    when (action) {
        HomeAction.NavigateBack -> {
            onNavigateBack()
        }
    }
}