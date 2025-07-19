package io.davidosemwota.moniepointx.features.calculate

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

}