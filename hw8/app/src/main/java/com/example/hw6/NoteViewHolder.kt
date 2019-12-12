package com.example.hw6

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_list_item.view.*
import java.text.SimpleDateFormat

class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view = itemView
    private var note: Note? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val context = itemView.context
        val intent = Intent(context, NoteActivity::class.java)
        intent.putExtra("note", note?.id)
        context.startActivity(intent)
    }

    fun bind(note: Note) {
        this.note = note
        view.noteText.text = note.text
        view.noteDate.text = SimpleDateFormat("dd.MM.yyyy").format(note.date)
        view.noteImage.setImageDrawable(view.context.getDrawable(note.drawableRes))
    }

}