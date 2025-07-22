package io.davidosemwota.moniepointx.features.calculate

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
class CalculateViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CalculateState())
    val state: StateFlow<CalculateState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<CalculateAction>()
    val action: SharedFlow<CalculateAction> = _action.asSharedFlow()

    fun sendAction(action: CalculateAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    fun onLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    fun onSelectedPackageOptionChanged(selectedOption: String) {
        _state.value = _state.value.copy(selectedPackageOption = selectedOption)
    }

    fun onSenderLocationChanged(location: String) {
        _state.value = _state.value.copy(senderLocation = location)
    }

    fun onReceiverLocationChanged(location: String) {
        _state.value = _state.value.copy(receiverLocation = location)
    }

    fun onPackageWeightChanged(weight: String) {
        _state.value = _state.value.copy(weight = weight)
    }

}