package com.mohaberabi.androidinternals.ipc.messengerservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class MusicMessengerManager(
    private val context: Context,
) {

    private var server: Messenger? = null
    private val _currentSong = MutableStateFlow("")
    val currentSong = _currentSong.asStateFlow()
    private val clientMessenger = Messenger(
        ClientIncomingHandler(
            onSongChanged = { song -> _currentSong.update { song } }
        )
    )
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            server = Messenger(service)
            val message = Message.obtain(null, MusicServiceCommands.Start.what)
            message.replyTo = clientMessenger
            server?.send(message)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            server = null
        }

        override fun onBindingDied(name: ComponentName?) {
            server = null
        }
    }

    fun bind() {
        Intent(context, MusicServiceMessenger::class.java).also {
            context.bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
        }

    }

    fun next() {
        val message = Message.obtain(null, MusicServiceCommands.Next.what)
        message.replyTo = clientMessenger
        server?.send(message)
    }

    fun previous() {
        val message = Message.obtain(null, MusicServiceCommands.Previous.what)
        message.replyTo = clientMessenger
        server?.send(message)
    }

    fun unbind() {
        val message = Message.obtain(null, MusicServiceCommands.Stop.what)
        message.replyTo = clientMessenger
        server?.send(message)
        server = null
    }
}