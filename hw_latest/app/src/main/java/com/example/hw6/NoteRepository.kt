package com.example.hw6

import java.util.*


object NoteRepository {
    private val NOTE_LIST: MutableMap<Long, Note> = hashMapOf(
        1L to Note(1, Date(1576174880000), "Lorem ipsum", R.drawable.image1),
        2L to Note(2, Date(1576002080000), "Second note", R.drawable.image2),
        3L to Note(3, Date(1575742880000), "Hello kitty", R.drawable.image3),
        4L to Note(4, Date(1575051680000), "I love Android", R.drawable.image4),
        5L to Note(5, Date(1572546080000), "Wow", R.drawable.image5)
        )

    fun listNotes(): List<Note> = NOTE_LIST.values.toList()

    fun getNoteWithId(id: Long): Note? = NOTE_LIST[id]

}