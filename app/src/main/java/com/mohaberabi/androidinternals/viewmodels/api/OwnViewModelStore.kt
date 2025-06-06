package com.mohaberabi.androidinternals.viewmodels.api

class OwnViewModelStore() {
    private val store = mutableMapOf<String, OwnViewModel>()

    fun <T : OwnViewModel> put(vm: T) {
        store[vm::class.java.simpleName] = vm
    }

    operator fun <T : OwnViewModel> get(key: String): T? = store[key] as? T

    fun clear() {
        store.values.forEach { it.onCleared() }
        store.clear()
    }

}
