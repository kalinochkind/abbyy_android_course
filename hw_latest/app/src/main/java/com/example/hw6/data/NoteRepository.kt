package com.example.hw6.data

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import java.util.Date


class NoteRepository(private val helper: SQLiteOpenHelper) {

    companion object {
        private val NOTE_PROJECTION = arrayOf(
            BaseColumns._ID,
            NoteContract.NoteEntry.DATE,
            NoteContract.NoteEntry.TEXT,
            NoteContract.NoteEntry.IMAGE
        )
    }

    fun listNotes(): List<Note> {
        val db = helper.readableDatabase
        val sortOrder = "${BaseColumns._ID} ASC"
        val cursor = db.query(NoteContract.TABLE_NAME, NOTE_PROJECTION, null, null,
            null, null, sortOrder)

        val notes = mutableListOf<Note>()

        cursor.use {
            with(it) {
                while (moveToNext()) {
                    val id = getInt(getColumnIndex(BaseColumns._ID))
                    val date = Date(getLong(getColumnIndex(NoteContract.NoteEntry.DATE)))
                    val text = getString(getColumnIndex(NoteContract.NoteEntry.TEXT))
                    val image = getString(getColumnIndex(NoteContract.NoteEntry.IMAGE))
                    notes.add(Note(id, date, text, image))
                }
            }
        }
        return notes
    }

    fun getNoteWithId(id: Int): Note? {
        val db = helper.readableDatabase

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(NoteContract.TABLE_NAME, NOTE_PROJECTION, selection, selectionArgs,
            null, null, null)


        cursor.use {
            with(it) {
                if (moveToFirst()) {
                    val id = getInt(getColumnIndex(BaseColumns._ID))
                    val date = Date(getLong(getColumnIndex(NoteContract.NoteEntry.DATE)))
                    val text = getString(getColumnIndex(NoteContract.NoteEntry.TEXT))
                    val image = getString(getColumnIndex(NoteContract.NoteEntry.IMAGE))
                    return Note(id, date, text, image)
                }
                return null
            }
        }
    }

    fun addNote(text: String, image: String) {
        val db = helper.writableDatabase

        val values = ContentValues()
        values.put(NoteContract.NoteEntry.TEXT, text)
        values.put(NoteContract.NoteEntry.IMAGE, image)
        values.put(NoteContract.NoteEntry.DATE, Date().time)
        db.insert(NoteContract.TABLE_NAME, null, values)
    }

}