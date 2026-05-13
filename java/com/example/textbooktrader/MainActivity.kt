package com.example.textbooktrader

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


//The home page is for landing on and moving on nothing really happens here.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<Button>(R.id.btnRegister)
        val txtNeedHelp = findViewById<TextView>(R.id.txtNeedHelp)

        // Navigating to the Register page ( main path of users)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Navigation to the help page when the user needs help with the application
        txtNeedHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }
}