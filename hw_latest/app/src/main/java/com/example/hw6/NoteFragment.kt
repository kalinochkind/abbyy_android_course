package com.example.hw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_note.view.*

class NoteFragment : Fragment() {

    companion object {
        const val NOTE_ID = "noteId"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_note, container, false)

        val note = NoteRepository.getNoteWithId(arguments?.getLong(NOTE_ID, 1) ?: 1)
        if (note != null) {
            view.noteText.text = note.text
            view.noteImage.setImageDrawable(activity?.getDrawable(note.drawableRes))
        }
        return view
    }

}