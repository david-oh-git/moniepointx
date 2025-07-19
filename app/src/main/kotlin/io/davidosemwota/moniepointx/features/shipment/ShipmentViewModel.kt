package io.davidosemwota.moniepointx.features.shipment

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
class ShipmentViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ShipmentState())
    val state: StateFlow<ShipmentState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ShipmentAction>()
    val action: SharedFlow<ShipmentAction> = _action.asSharedFlow()

    fun sendAction(action: ShipmentAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    fun onLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

}