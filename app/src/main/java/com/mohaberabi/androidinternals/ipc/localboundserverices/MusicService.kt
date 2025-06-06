package com.mohaberabi.androidinternals.ipc.localboundserverices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MusicService : Service() {
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

    inner class MusicServiceBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private val _currentIndex = MutableStateFlow(0)
    private val currentIndex = _currentIndex.asStateFlow()
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val currentSong = _currentIndex.map { songs[it] }
        .stateIn(
            scope = serviceScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = songs[0]
        )

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

    private val binder = MusicServiceBinder()
    override fun onBind(intent: Intent?): IBinder = binder
    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}