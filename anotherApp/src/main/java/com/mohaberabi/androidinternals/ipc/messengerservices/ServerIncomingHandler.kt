package com.mohaberabi.androidinternals.ipc.messengerservices

import android.os.Handler
import android.os.Looper
import android.os.Message

class ServerIncomingHandler(
    private val service: MusicServiceInterface
) : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
        val command = MusicServiceCommands.entries.firstOrNull { msg.what == it.what }
        command?.let {
            when (it) {
                MusicServiceCommands.Previous -> service.previous()
                MusicServiceCommands.Next -> service.next()
                MusicServiceCommands.Start -> {
                    val client = msg.replyTo
                    if (client != null && service.clients.contains(client).not()) {
                        service.clients.add(client)
                        service.sendCurrentPlayingSong(client)
                    } else {
                    }
                }

                MusicServiceCommands.Stop -> {
                    val client = msg.replyTo
                    service.clients.remove(client)
                }
            }
        }
    }


}