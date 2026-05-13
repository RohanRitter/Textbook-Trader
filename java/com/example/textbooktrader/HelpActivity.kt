package com.example.textbooktrader

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// The help page consists of navigational links the XML has more to offer
class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnBooks = findViewById<Button>(R.id.btnBooks)
        val btnHelp = findViewById<Button>(R.id.btnHelp)

        // Goes to the home page
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Goes to the Book page
        btnBooks.setOnClickListener {
            val intent = Intent(this, BooksActivity::class.java)
            startActivity(intent)
        }

        // Reload the page because already on the Help page
        btnHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }
}