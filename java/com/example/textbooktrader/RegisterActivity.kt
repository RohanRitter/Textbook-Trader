package com.example.textbooktrader

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Same as home page, i only added navigation here as the registration is not needed required to work
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton = findViewById<Button>(R.id.btnCompleteRegister)
        val homeButton = findViewById<ImageButton>(R.id.btnHome)
        val txtNeedHelp = findViewById<TextView>(R.id.txtNeedHelp)

        // Register button (since we dont need register to work in this one it will always work)
        registerButton.setOnClickListener {
            val intent = Intent(this, BooksActivity::class.java)
            startActivity(intent)
        }

        // Home button incase we want too go back and see that cute shark in big screen one more time
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Same as home page goes to the help page
        txtNeedHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }
}