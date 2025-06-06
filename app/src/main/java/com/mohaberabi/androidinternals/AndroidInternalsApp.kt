package com.mohaberabi.androidinternals

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


class AndroidInternalsApp() : Application() {
    val appScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
}