package com.example.hw6

import android.app.Application
import com.example.hw6.data.AppSqliteOpenHelper
import com.example.hw6.data.NoteRepository

class App: Application() {
    companion object {
        lateinit var noteRepository: NoteRepository private set
    }
    override fun onCreate() {
        super.onCreate()
        noteRepository = NoteRepository(AppSqliteOpenHelper(this))
    }
}