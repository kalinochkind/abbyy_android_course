package com.example.hw6

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.data.Note
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.note_list_item.view.*
import kotlinx.android.synthetic.main.note_list_item.view.noteImage
import kotlinx.android.synthetic.main.note_list_item.view.noteText
import java.io.File
import java.text.SimpleDateFormat

class NoteViewHolder(itemView: View, private val clickHandler: (Int) -> Unit):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view = itemView
    private var note: Note? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        note?.let {
            clickHandler(it.id)
        }
    }

    fun bind(note: Note) {
        this.note = note
        view.noteText.text = note.text
        view.noteDate.text = SimpleDateFormat(view.context.getString(R.string.date_format)).format(note.date)
        Picasso.with(view.context).load(File(note.drawablePath))
            .fit().centerInside().into(view.noteImage)
    }

}