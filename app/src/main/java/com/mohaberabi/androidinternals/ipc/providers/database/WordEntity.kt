package com.mohaberabi.androidinternals.ipc.providers.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    "word"
)
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val define: String,
    val word: String,
)
