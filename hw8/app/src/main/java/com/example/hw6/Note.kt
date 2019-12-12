package com.example.hw6

import java.io.Serializable
import java.util.*

data class Note(val id: Long, val date: Date, val text: String, val drawableRes: Int): Serializable