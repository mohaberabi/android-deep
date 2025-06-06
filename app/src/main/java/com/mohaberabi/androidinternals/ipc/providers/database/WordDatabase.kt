package com.mohaberabi.androidinternals.ipc.providers.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [WordEntity::class],
    version = 1
)
abstract class WordDatabase : RoomDatabase() {
    abstract val dao: WordDao
}