package com.example.hw6

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_note.view.*

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val noteId = intent.getSerializableExtra("note") as Long?
        title = getString(R.string.note_title)

        val view = findViewById<View>(R.id.coordinatorLayout)

        val note = NoteRepository.getNoteWithId(noteId ?: 1)
        if (note != null) {
            view.noteText.text = note.text
            view.noteImage.setImageDrawable(getDrawable(note.drawableRes))
        }
    }

}
