package com.mohaberabi.androidinternals.ipc.servicemessenger


enum class MusicServiceCommands(
    val what: Int
) {
    Previous(0),
    Next(1),
    Start(2),
    Stop(3)
}


enum class MusicServiceClientCommands(
    val what: Int
) {
    SongChanged(0)
}