package com.example.hw6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val card = findViewById<View>(R.id.cardView)
        val detail = findViewById<View>(R.id.detail_layout)
        title = "Notes"
        card.setOnClickListener {
            title = "Note 1"
            card.visibility = View.GONE
            detail.visibility = View.VISIBLE
        }
    }
}
