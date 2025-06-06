package com.mohaberabi.androidinternals.ipc.providers.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface WordDao {
    @Upsert
    suspend fun insertAll(words: List<WordEntity>)

    @Query("SELECT count(*) FROM word")
    suspend fun getCount(): Long

    @Query("SELECT * FROM word")
    fun getAll(): Cursor

    @Query("SELECT * FROM word WHERE lower(word)= lower(:word)")
    fun findByWord(word: String): Cursor
}