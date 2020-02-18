package com.example.hw6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


const val NOTE_FRAGMENT_TAG = "note"

class MainActivity : AppCompatActivity(), MainFragment.OpenNoteListener {

    var noteId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.notes_title)

        if (supportFragmentManager.findFragmentByTag(NOTE_FRAGMENT_TAG) == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, MainFragment())
            fragmentTransaction.commit()
        }
    }

    override fun openNote(id: Long) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack("note")
        val fragment = NoteFragment()

        val args = Bundle()
        args.putLong(NoteFragment.NOTE_ID, id)
        fragment.arguments = args

        noteId = id
        fragmentTransaction.replace(R.id.fragment_container, fragment, NOTE_FRAGMENT_TAG)
        fragmentTransaction.commit()
    }

}
