package com.example.hw6

import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.data.Note
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.note_list_item.view.*
import java.io.File
import java.text.SimpleDateFormat

class NoteViewHolder(itemView: View, private val actionHandler: NoteActionHandler):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    interface NoteActionHandler {
        fun handleClick(id: Int)
        fun handleShareNote(id: Int)
        fun handleDeleteNote(id: Int)
    }

    private var view = itemView
    private var note: Note? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        note?.let {
            actionHandler.handleClick(it.id)
        }
    }

    private fun showPopupMenu(view: View, noteId: Int) {
        val menu = PopupMenu(view.context, view.noteMenu, Gravity.END)
        menu.inflate(R.menu.note_list_menu)
        menu.setOnMenuItemClickListener {
            handleMenuClick(it, noteId)
            true
        }
        menu.show()
    }

    private fun handleMenuClick(item: MenuItem, noteId: Int) {
        when(item.itemId) {
            R.id.popup_item_share -> actionHandler.handleShareNote(noteId)
            R.id.popup_item_delete -> actionHandler.handleDeleteNote(noteId)
        }
    }

    fun bind(note: Note) {
        this.note = note
        view.noteText.text = note.text
        view.noteDate.text = SimpleDateFormat(view.context.getString(R.string.date_format)).format(note.date)
        Picasso.with(view.context).load(File(note.drawablePath))
            .fit().centerInside().into(view.noteImage)
        view.noteMenu.setOnClickListener {
            showPopupMenu(it, note.id)
        }
    }

}