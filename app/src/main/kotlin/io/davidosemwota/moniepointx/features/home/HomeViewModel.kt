package io.davidosemwota.moniepointx.features.home

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
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<HomeAction>()
    val action: SharedFlow<HomeAction> = _action.asSharedFlow()

    fun sendAction(action: HomeAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    fun onLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    fun onReceiptNumberChanged(receiptNumber: String) {
        _state.value = _state.value.copy(receiptNumber = receiptNumber)
    }

}