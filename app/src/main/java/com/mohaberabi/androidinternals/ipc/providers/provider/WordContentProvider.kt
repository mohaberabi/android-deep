package com.mohaberabi.androidinternals.ipc.providers.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.mohaberabi.androidinternals.AndroidInternalsApp
import com.mohaberabi.androidinternals.ipc.providers.database.WordDao
import com.mohaberabi.androidinternals.ipc.providers.database.WordDatabase
import com.mohaberabi.androidinternals.ipc.providers.database.WordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.csv.CSVFormat
import kotlin.coroutines.coroutineContext

class WordContentProvider : ContentProvider() {
    private lateinit var database: WordDatabase
    private lateinit var dao: WordDao
    private val application by lazy {
        requireNotNull(context).applicationContext as AndroidInternalsApp
    }

    companion object {
        private const val AUTHORITY = "com.mohaberabi.word.provider"
        private const val WORDS = 100
        private const val TYPE = "vnd.android.cursor.dir/vnd.$AUTHORITY"
        private val matcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "words", WORDS)
        }

    }

    override fun onCreate(): Boolean {
        database = Room.databaseBuilder(
            context = application.applicationContext,
            klass = WordDatabase::class.java,
            name = "word.db"
        ).build()
        dao = database.dao
        application.appScope.launch {
            saveDataIfFirstTime()
        }
        return true
    }


    private suspend fun saveDataIfFirstTime() = withContext(Dispatchers.IO) {
        val count = dao.getCount()
        if (count <= 0) {
            val words = getWordsFromCsv()
            dao.insertAll(words)
        }
    }

    private suspend fun getWordsFromCsv() = runCatching {
        application.assets.open(
            "english-dict.csv",
        ).use { input ->
            val records = CSVFormat.DEFAULT.parse(input.bufferedReader())
            records.drop(1)
                .mapNotNull { row ->
                    val word = runCatching { row.get(0) }.getOrDefault("")
                    val define = runCatching { row.get(1) }.getOrDefault("")
                    WordEntity(word = word, define = define)
                }

        }
    }.onFailure {
        coroutineContext.ensureActive()
    }.getOrDefault(emptyList())

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        return when (matcher.match(uri)) {
            WORDS -> selectionArgs?.getOrNull(0)?.let {
                dao.findByWord(it)
            } ?: dao.getAll()

            else -> error("Invalid Query Data")
        }
    }

    override fun getType(
        uri: Uri,
    ): String = TYPE

    override fun insert(
        uri: Uri,
        values: ContentValues?
    ): Uri? = null

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}