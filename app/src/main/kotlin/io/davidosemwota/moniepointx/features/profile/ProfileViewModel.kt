package io.davidosemwota.moniepointx.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ProfileAction>()
    val action: SharedFlow<ProfileAction> = _action.asSharedFlow()

    fun sendAction(action: ProfileAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    fun onLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

}