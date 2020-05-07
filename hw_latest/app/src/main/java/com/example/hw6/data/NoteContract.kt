package com.example.hw6.data

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

object NoteContract {

    const val TABLE_NAME = "note"

    object NoteEntry: BaseColumns {
        const val DATE = "date"
        const val TEXT = "text"
        const val IMAGE = "image"
    }

    private const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    private const val SQL_CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${NoteEntry.DATE} INTEGER NOT NULL," +
                "${NoteEntry.TEXT} TEXT NOT NULL," +
                "${NoteEntry.IMAGE} TEXT NOT NULL)"

    fun createTable(db: SQLiteDatabase?) {
        if (db === null) {
            return
        }
        db.execSQL(SQL_DROP_TABLE)
        db.execSQL(SQL_CREATE_TABLE)
    }

}