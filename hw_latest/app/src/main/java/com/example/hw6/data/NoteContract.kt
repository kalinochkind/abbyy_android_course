package com.example.hw6.data

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.hw6.R

object NoteContract {

    const val TABLE_NAME = "note"

    object NoteEntry: BaseColumns {
        const val DATE = "date"
        const val TEXT = "text"
        const val IMAGE_ID = "image_id"
    }

    private const val SQL_CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${NoteEntry.DATE} INTEGER NOT NULL," +
                "${NoteEntry.TEXT} TEXT NOT NULL," +
                "${NoteEntry.IMAGE_ID} INTEGER NOT NULL)"


    private val NOTE_LIST: List<Triple<Long, String, Int>> = listOf(
        Triple(
            1576174880000,
            "Lorem ipsum",
            R.drawable.image1
        ),
        Triple(
            1576002080000,
            "Second note",
            R.drawable.image2
        ),
        Triple(
            1575742880000,
            "Hello kitty",
            R.drawable.image3
        ),
        Triple(
            1575051680000,
            "I love Android",
            R.drawable.image4
        ),
        Triple(
            1572546080000,
            "Wow",
            R.drawable.image5
        )
    )

    fun createTable(db: SQLiteDatabase?) {
        if (db === null) {
            return
        }
        db.execSQL(SQL_CREATE_TABLE)
        NOTE_LIST.forEach {
            val values = ContentValues()
            values.put(NoteEntry.DATE, it.first)
            values.put(NoteEntry.TEXT, it.second)
            values.put(NoteEntry.IMAGE_ID, it.third)
            db.insert(TABLE_NAME, null, values)
        }
    }

}