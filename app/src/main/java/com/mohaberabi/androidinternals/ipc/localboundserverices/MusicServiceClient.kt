package com.mohaberabi.androidinternals.ipc.localboundserverices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MusicServiceClient(
    private val context: Context,
    private val scope: CoroutineScope
) {


    private lateinit var service: MusicService
    private val _isBounded = MutableStateFlow(false)
    val isBounded = _isBounded.asStateFlow()
    val currentSong = isBounded
        .flatMapLatest { bound ->
            if (bound) {
                service.currentSong
            } else {
                flowOf("Waiting for the music player to work ")
            }
        }.stateIn(
            scope,
            SharingStarted.Lazily,
            "Waiting for the music player to work "
        )

    fun bind() {
        val intent = Intent(context, MusicService::class.java)
        context.bindService(
            intent,
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    fun unbind() {
        context.unbindService(serviceConnection)
        _isBounded.update { false }
    }

    fun next() = service.next()
    fun previous() = service.previous()
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            val binder = service as MusicService.MusicServiceBinder
            this@MusicServiceClient.service = binder.getService()
            _isBounded.update { true }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            _isBounded.update { false }
        }

        override fun onBindingDied(name: ComponentName?) {
            super.onBindingDied(name)
            _isBounded.update { false }

        }

        override fun onNullBinding(name: ComponentName?) {
            super.onNullBinding(name)
            _isBounded.update { false }
        }
    }
}