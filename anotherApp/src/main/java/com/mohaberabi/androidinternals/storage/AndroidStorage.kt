package com.mohaberabi.androidinternals.storage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


class AndroidStorage(
    private val context: Context,
) {
    private suspend fun writeToInternalStorage() = withContext(Dispatchers.IO) {
        val filesDir = context.filesDir
        val someText = "iam internal"
        val file = File(filesDir, "internalfile.txt")
        file.writeText(someText)
        file.writeBytes(someText.encodeToByteArray())
        FileOutputStream(file).use { stream ->
            stream.write(someText.encodeToByteArray())
        }
    }

    private suspend fun writeToPrivateExternalStorage() = withContext(Dispatchers.IO) {
        //[null] is type means like for music or photos ...etc
        val privateExternalStorage = context.getExternalFilesDir(null)
        val someText = "iam external private"
        val file = File(privateExternalStorage, "privateexternal.txt")
        file.writeText(someText)
        file.writeBytes(someText.encodeToByteArray())
        FileOutputStream(file).use { stream ->
            stream.write(someText.encodeToByteArray())
        }
    }
  
    private suspend fun readUriAsBitmap(uri: Uri): Bitmap? = withContext(Dispatchers.IO) {
        val bytes = context.contentResolver.openInputStream(uri)?.use { input -> input.readBytes() }
        bytes?.let {
            BitmapFactory.decodeByteArray(bytes, 0, it.size)
        }
    }
}