package com.mohaberabi.androidinternals.ipc.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.mohaberabi.androidinternals.aidl.IMusicService
import com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MusicServiceAidController(
    private val context: Context
) {
    private var service: IMusicService? = null
    private val _currentSong = MutableStateFlow("")
    val currentSong = _currentSong.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    private val songChangedCallBack = object : ISongNameChangedCallback.Stub() {
        override fun onSongNameChanged(name: String?) {
            name?.let { sng ->
                _currentSong.update { sng }
            }
        }

    }

    fun next() {
        service?.next()
    }

    fun previous() {
        service?.previous()
    }

    fun bind() {
        if (!_isConnected.value) {
            val intent = Intent("com.mohaberabi.BIND_TO_MUSIC_SERVICE").also {
                it.`package` = "com.mohaberabi.androidinternals"
            }
            context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }

    }

    fun unbind() {
        if (_isConnected.value) {
            context.unbindService(serviceConnection)
            service?.unregisterCallBack(songChangedCallBack)
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            this@MusicServiceAidController.service = IMusicService.Stub.asInterface(service)
            val bindService = this@MusicServiceAidController.service
            _isConnected.update { true }
            bindService?.registerCallBack(songChangedCallBack)
            _currentSong.update {
                bindService?.currentSongName ?: ""
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            service = null
            _isConnected.update { false }
        }

    }
}