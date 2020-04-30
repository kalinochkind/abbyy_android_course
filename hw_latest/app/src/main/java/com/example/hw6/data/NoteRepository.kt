package com.example.hw6.data

import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import java.util.Date


class NoteRepository(private val helper: SQLiteOpenHelper) {

    companion object {
        private val NOTE_PROJECTION = arrayOf(
            BaseColumns._ID,
            NoteContract.NoteEntry.DATE,
            NoteContract.NoteEntry.TEXT,
            NoteContract.NoteEntry.IMAGE_ID
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
                    val imageId = getInt(getColumnIndex(NoteContract.NoteEntry.IMAGE_ID))
                    notes.add(Note(id, date, text, imageId))
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
                    val imageId = getInt(getColumnIndex(NoteContract.NoteEntry.IMAGE_ID))
                    return Note(id, date, text, imageId)
                }
                return null
            }
        }
    }

}