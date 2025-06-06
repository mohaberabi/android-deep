package com.mohaberabi.androidinternals.ipc.messengerservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import androidx.core.os.bundleOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class MusicServiceMessenger : Service() {

    companion object {
        private val songs = listOf(
            "Some Lovely Music 1",
            "Some Lovely Music 2",
            "Some Lovely Music 3",
            "Some Lovely Music 4",
            "Some Lovely Music 5",
            "Some Lovely Music 6",
            "Some Lovely Music 7",
            "Some Lovely Music 8",
            "Some Lovely Music 9",
            "Some Lovely Music 10",
        )
    }

    private val clients = mutableListOf<Messenger>()
    private lateinit var messenger: Messenger
    private val _currentIndex = MutableStateFlow(0)
    private val currentIndex = _currentIndex.asStateFlow()
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    fun previous() {
        if (_currentIndex.value == 0) {
            _currentIndex.update { songs.lastIndex }
        } else {
            _currentIndex.update { it - 1 }
        }

    }

    fun next() {
        if (_currentIndex.value == songs.lastIndex) {
            _currentIndex.update { 0 }
        } else {
            _currentIndex.update { it + 1 }
        }
    }

    private fun sendCurrentSong(
        client: Messenger,
        song: String,
    ) {
        val what = MusicServiceClientCommands.SongChanged.what
        val message = Message.obtain(null, what)
        message.data = bundleOf(MusicServiceClientCommands.SongChanged.name to song)
        try {
            client.send(message)
        } catch (e: Exception) {
            clients.remove(client)
        }
    }

    private val service = object :
        MusicServiceInterface {
        override val clients: MutableList<Messenger>
            get() = this@MusicServiceMessenger.clients

        override fun next() = this@MusicServiceMessenger.next()

        override fun previous() = this@MusicServiceMessenger.previous()

        override fun sendCurrentPlayingSong(messenger: Messenger) =
            this@MusicServiceMessenger.sendCurrentSong(messenger, songs[_currentIndex.value])

    }

    override fun onCreate() {
        super.onCreate()
        currentIndex.onEach { index ->
            val song = songs[index]
            clients.forEach { sendCurrentSong(it, song) }
        }.launchIn(serviceScope)

    }

    override fun onBind(intent: Intent?): IBinder {
        val handler = ServerIncomingHandler(service)
        messenger = Messenger(handler)
        return messenger.binder
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.coroutineContext.cancelChildren()
    }


}