package com.mohaberabi.androidinternals.ipc.messengerservices

import android.os.Messenger

interface MusicServiceInterface {

    val clients: MutableList<Messenger>
    fun next()
    fun previous()
    fun sendCurrentPlayingSong(messenger: Messenger)
}