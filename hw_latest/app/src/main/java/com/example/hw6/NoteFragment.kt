package com.example.hw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_note.view.*
import kotlinx.coroutines.*

class NoteFragment : Fragment() {

    companion object {
        private const val NOTE_ID = "noteId"

        fun newInstance(id: Int): NoteFragment {
            val fragment = NoteFragment()
            val args = Bundle()
            args.putInt(NOTE_ID, id)
            fragment.arguments = args
            return fragment
        }
    }

    var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteId = arguments?.getInt(NOTE_ID, 1) ?: 1
        job = GlobalScope.launch(context = Dispatchers.Main) {
            val note = queryNote(noteId)
            if (note != null) {
                view.noteText.text = note.text
                view.noteImage.setImageDrawable(activity?.getDrawable(note.drawableRes))
            }
        }
    }

    fun getNoteId(): Int? = arguments?.getInt(NOTE_ID)

    suspend fun queryNote(id: Int) = withContext(Dispatchers.IO) {
        return@withContext App.noteRepository.getNoteWithId(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

}