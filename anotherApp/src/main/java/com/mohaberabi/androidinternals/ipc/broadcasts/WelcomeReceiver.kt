package com.mohaberabi.androidinternals.ipc.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

const val EXPLICIT_ACTION = "com.mohaberabi.androidinternal.explicit.broadcast"
const val EXTRA_KEY = "welcome"

val messageFlow = MutableStateFlow("")

class WelcomeReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {

        println("WelcomeReceiver___Received")
        intent?.let { found ->
            val message = found.getStringExtra(EXTRA_KEY) ?: ""
            messageFlow.update { message }
        }

    }
}