package com.example.hw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class NoteListFragment : Fragment() {

    interface OpenNoteListener {
        fun openNote(id: Int)
    }

    var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = NoteAdapter(emptyList()) {}
        populateView(recyclerView)
        return view
    }

    suspend fun queryNotes() = withContext(Dispatchers.IO) {
        return@withContext App.noteRepository.listNotes()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    private fun populateView(recyclerView: RecyclerView) {
        job = GlobalScope.launch(context = Dispatchers.Main) {
            val scroll = recyclerView.scrollY
            val notes = queryNotes()
            recyclerView.adapter = NoteAdapter(notes) {
                val listener = activity as OpenNoteListener
                listener.openNote(it)
            }
            recyclerView.scrollY = scroll
        }
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.recyclerView) ?: return
        populateView(recyclerView)
    }

}