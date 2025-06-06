package com.mohaberabi.androidinternals.ipc.messengerservices

import android.os.Handler
import android.os.Looper
import android.os.Message

class ClientIncomingHandler(
    private val onSongChanged: (String) -> Unit,
) : Handler(Looper.getMainLooper()) {

    override fun handleMessage(msg: Message) {
        if (msg.what == MusicServiceClientCommands.SongChanged.what) {
            val message = msg.data.getString(MusicServiceClientCommands.SongChanged.name)
                ?: "Received an empty song "
            onSongChanged(message)
        }

    }
}