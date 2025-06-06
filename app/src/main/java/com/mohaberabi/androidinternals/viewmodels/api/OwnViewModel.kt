package com.mohaberabi.androidinternals.viewmodels.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class OwnViewModel {
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    open fun onCleared() {
        viewModelScope.cancel()
    }
}



