package com.mohaberabi.androidinternals.ipc.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import com.mohaberabi.androidinternals.aidl.IMusicService
import com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class MusicServiceAidl() : Service() {
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

    private val _currentIndex = MutableStateFlow(0)
    private val currentIndex = _currentIndex.asStateFlow()
    private val remoteCallBacks = RemoteCallbackList<ISongNameChangedCallback>()
    private val lock = Any()
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onBind(intent: Intent?): IBinder? {
        return object : IMusicService.Stub() {
            override fun next() = this@MusicServiceAidl.next()

            override fun previous() = this@MusicServiceAidl.previous()

            override fun getCurrentSongName(): String = songs[_currentIndex.value]

            override fun registerCallBack(callback: ISongNameChangedCallback?) {
                remoteCallBacks.register(callback)
            }

            override fun unregisterCallBack(callback: ISongNameChangedCallback?) {
                remoteCallBacks.unregister(callback)
            }

        }


    }

    override fun onCreate() {
        super.onCreate()
        _currentIndex.onEach {
            broadcastSongName(songs[it])
        }.launchIn(serviceScope)
    }

    fun next() {
        synchronized(lock) {
            if (_currentIndex.value == songs.lastIndex) {
                _currentIndex.update { 0 }
            } else {
                _currentIndex.update { it + 1 }
            }
        }
    }

    fun previous() {
        synchronized(lock) {
            if (_currentIndex.value == 0) {
                _currentIndex.update { songs.lastIndex }
            } else {
                _currentIndex.update { it - 1 }
            }
        }
    }

    private fun broadcastSongName(song: String) {
        val failed = mutableListOf<ISongNameChangedCallback>()
        val count = remoteCallBacks.beginBroadcast()
        for (i in 0 until count) {
            val callback = remoteCallBacks.getBroadcastItem(i)
            try {
                callback.onSongNameChanged(song)
            } catch (e: Exception) {
                e.printStackTrace()
                failed.add(callback)
            }
        }
        remoteCallBacks.finishBroadcast()
        failed.forEach {
            remoteCallBacks.unregister(it)
        }
    }
}