package com.example.hw6

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class NoteListFragment : Fragment(), NoteViewHolder.NoteActionHandler {

    interface OpenNoteListener {
        fun openNote(id: Int)
    }

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = NoteAdapter(emptyList(), this)
        populateView(recyclerView)
        return view
    }

    private suspend fun queryNotes() = withContext(Dispatchers.IO) {
        return@withContext App.noteRepository.listNotes()
    }

    private suspend fun getNote(id: Int) = withContext(Dispatchers.IO) {
        return@withContext App.noteRepository.getNoteWithId(id)
    }

    private suspend fun deleteNote(id: Int) = withContext(Dispatchers.IO) {
        return@withContext App.noteRepository.deleteNote(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    private fun populateView(recyclerView: RecyclerView) {
        job = GlobalScope.launch(context = Dispatchers.Main) {
            val scroll = recyclerView.scrollY
            val notes = queryNotes()
            recyclerView.adapter = NoteAdapter(notes, this@NoteListFragment)
            recyclerView.scrollY = scroll
        }
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.recyclerView) ?: return
        populateView(recyclerView)
    }

    override fun handleClick(id: Int) {
        val listener = activity as OpenNoteListener
        listener.openNote(id)
    }

    override fun handleShareNote(id: Int) {
        job = GlobalScope.launch(context = Dispatchers.Main) {
            val note = getNote(id) ?: return@launch
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, note.text)
            sendIntent.type = "text/plain"
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun handleDeleteNote(id: Int) {
        AlertDialog.Builder(context)
            .setMessage(getString(R.string.popup_delete_confirm))
            .setPositiveButton(R.string.yes) {_, _ ->
                job = GlobalScope.launch(context = Dispatchers.Main) {
                    deleteNote(id)
                    activity?.findViewById<RecyclerView>(R.id.recyclerView)?.let {
                        populateView(it)
                    }
                }
            }
            .setNegativeButton(R.string.no) {_, _ ->}
            .show()
    }

}