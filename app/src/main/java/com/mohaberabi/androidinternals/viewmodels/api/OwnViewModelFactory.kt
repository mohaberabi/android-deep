package com.mohaberabi.androidinternals.viewmodels.api

interface OwnViewModelFactory {
    fun <T : OwnViewModel> create(
        clazz: Class<T>
    ): T
}

object NoConstructorOwnViewModelFactory : OwnViewModelFactory {
    override fun <T : OwnViewModel> create(clazz: Class<T>): T {
        return clazz.getDeclaredConstructor().newInstance()
    }
}
