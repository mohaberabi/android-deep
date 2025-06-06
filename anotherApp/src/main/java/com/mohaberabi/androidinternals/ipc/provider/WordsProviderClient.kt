package com.mohaberabi.androidinternals.ipc.provider

import android.content.Context
import android.database.Cursor
import androidx.core.database.getStringOrNull
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


data class WordModel(
    val define: String,
    val word: String,
)


class WordsProviderClient(
    private val context: Context
) {
    companion object {
        private const val AUTHORITY = "com.mohaberabi.word.provider"
        private const val URI = "content://$AUTHORITY/words"
    }

    suspend fun searchWords(query: String) = withContext(Dispatchers.IO) {
        context.contentResolver.query(
            URI.toUri(),
            null,
            null,
            arrayOf(query),
            null,
        ).use { cursor -> mapWords(cursor) }
    }

    suspend fun getWords() = withContext(Dispatchers.IO) {
        context.contentResolver.query(
            URI.toUri(),
            null,
            null,
            null,
            null
        ).use { cursor -> mapWords(cursor) }

    }

    private fun mapWords(
        cursor: Cursor?,
    ): List<WordModel> {
        if (cursor == null) return listOf()
        val list = mutableListOf<WordModel>()
        val wordIndex = cursor.getColumnIndexOrThrow("word")
        val defineIndex = cursor.getColumnIndexOrThrow("define")
        while (cursor.moveToNext()) {
            val word = cursor.getStringOrNull(wordIndex) ?: "Null"
            val define = cursor.getStringOrNull(defineIndex) ?: "Null"
            list.add(WordModel(word = word, define = define))
        }
        return list
    }
}