package io.davidosemwota.moniepointx.features.profile

sealed interface ProfileAction {

    data object NavigateBack : ProfileAction
}

fun onAction(
    action: ProfileAction,
    onNavigateBack: () -> Unit,
    viewModel: ProfileViewModel,
) {
    when (action) {
        ProfileAction.NavigateBack -> {
            onNavigateBack()
        }
    }
}