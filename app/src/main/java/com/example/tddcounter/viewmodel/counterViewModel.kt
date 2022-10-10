package com.example.tddcounter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tddcounter.events.CounterEvents
import com.example.tddcounter.state.CounterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel
    : ViewModel(), CounterEvents {

    companion object {
        private const val TAG = "CounterViewModel"
    }

    private val _state = MutableStateFlow(CounterState.default)
    val state: StateFlow<CounterState>
        get() = _state



    override fun restartClicked() {
        Log.d(TAG, "Count before reset: ${state.value.counterValue}")
        _state.update {
            it.copy(
                counterValue = 0
            )
        }
        Log.d(TAG, "Count after reset: ${state.value.counterValue}")
    }

    override fun incrementClicked() {
        Log.d(TAG, "Count before increment: ${state.value.counterValue}")
        _state.update {
            it.copy(
                counterValue = state.value.counterValue + 1
            )
        }
        Log.d(TAG, "Count after increment: ${state.value.counterValue}")
    }

    override fun decrementClicked() {
        Log.d(TAG, "Count before decrement: ${state.value.counterValue}")
        _state.update {
            it.copy(
                counterValue = state.value.counterValue - 1
            )
        }
        Log.d(TAG, "Count after decrement: ${state.value.counterValue}")
    }


}