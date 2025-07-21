package io.davidosemwota.moniepointx.features.shipmenthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipmentHistoryViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ShipmentHistoryState())
    val state: StateFlow<ShipmentHistoryState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ShipmentHistoryAction>()
    val action: SharedFlow<ShipmentHistoryAction> = _action.asSharedFlow()

    fun sendAction(action: ShipmentHistoryAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    fun onLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    fun onSelectedTabIndexChanged(index: Int) {
        _state.value = _state.value.copy(selectedTabIndex = index)
    }

}