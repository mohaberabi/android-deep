package com.mohaberabi.androidinternals.viewmodels.api

class OwnViewModelProvider(
    private val factory: OwnViewModelFactory,
    private val store: OwnViewModelStore
) {

    operator fun <T : OwnViewModel> get(clazz: Class<T>): T {
        val key = clazz.simpleName
        val existing = store.get<T>(key)
        return if (existing != null && clazz.isInstance(existing)) {
            existing
        } else {
            val created = factory.create(clazz)
            store.put(created)
            return created
        }
    }
}