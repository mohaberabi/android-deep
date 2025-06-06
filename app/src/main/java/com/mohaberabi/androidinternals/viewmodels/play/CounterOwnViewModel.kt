package com.mohaberabi.androidinternals.viewmodels.play

import com.mohaberabi.androidinternals.viewmodels.api.OwnViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random


private suspend fun getInitialCounter(): Int {
    delay(3000L)
    return 10
}

data class CounterState(
    val loading: Boolean = false,
    val counter: Int = 0
)

class CounterOwnViewModel : OwnViewModel() {
    private val _state = MutableStateFlow(CounterState())
    val state = _state.asStateFlow()

    init {
        getCounter()
    }

    private fun getCounter() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            val counter = getInitialCounter()
            _state.update { it.copy(counter = counter) }
            _state.update { it.copy(loading = false) }

        }
    }

    fun incrementCounter() = _state.update { it.copy(counter = it.counter + 1) }
}