package com.example.datatransferdemo.pageb

import android.content.Context

class FileHelper(private val context: Context) {

    fun writeToFile(fileName: String, data: String) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
            outputStream.write(data.toByteArray())
        }
    }

    fun readFromFile(fileName: String): String {
        return context.openFileInput(fileName).bufferedReader().useLines { lines ->
            lines.fold("") { some, text ->
                "$some\n$text"
            }
        }
    }
}
