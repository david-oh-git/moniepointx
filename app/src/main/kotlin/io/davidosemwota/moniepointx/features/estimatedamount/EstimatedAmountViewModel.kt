package io.davidosemwota.moniepointx.features.estimatedamount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstimatedAmountViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(EstimatedAmountState())
    val state: StateFlow<EstimatedAmountState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<EstimatedAmountAction>()
    val action: SharedFlow<EstimatedAmountAction> = _action.asSharedFlow()

    fun sendAction(action: EstimatedAmountAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    fun onLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    fun onAmountChanged(amount: Int) {
        _state.value = _state.value.copy(amount = amount)
    }

    fun animateAmount() {
        viewModelScope.launch {
            val state = _state.value
            for (i in 1001 .. 1400) {
                _state.value = state.copy(amount = i)
                delay(2L) // adjust for speed
            }
        }
    }

}