package com.example.bookshub.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookshub.R

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val authors = intent.getStringExtra("authors")
        val url = intent.getStringExtra("url")

        val bookImage = findViewById<ImageView>(R.id.detail_image)
        val bookTitle = findViewById<TextView>(R.id.detail_title)
        val bookAuthors = findViewById<TextView>(R.id.detail_authors)
        val readButton = findViewById<Button>(R.id.read_book_button)

        Glide.with(this).load(image).into(bookImage)
        bookTitle.text = title
        bookAuthors.text = authors

        readButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }
}
