package com.mohaberabi.androidinternals.architecture.volatilemainstate

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel() {


    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    fun increment() = _counter.update { it + 1 }
}