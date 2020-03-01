package com.example.hw6.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppSqliteOpenHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        const val DATABASE_NAME = "Notes.db"
        const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        NoteContract.createTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // ¯\_(ツ)_/¯
    }

}