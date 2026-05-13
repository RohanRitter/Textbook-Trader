package com.example.textbooktrader

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// In this inquiries page the books chosen will be displayed here
// Finalize a trade by selecting it again and choosing a date and time
class InquiriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inquiries)

        val inquiryContainer = findViewById<LinearLayout>(R.id.inquiryContainer)
        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnBooks = findViewById<Button>(R.id.btnBooks)
        val btnHelp = findViewById<Button>(R.id.btnHelp)

        // For loop to display books chosen for inquiry
        for (book in BookRepository.inquiryBooks) {
            val textView = TextView(this)
            textView.text = "${book.bookName} - ${book.price}"
            textView.textSize = 20f
            textView.setPadding(20, 20, 20, 20)
            inquiryContainer.addView(textView)

            // The clicker to open the Scedule dialog so students can setup their trade
            textView.setOnClickListener {
                val dialogView = layoutInflater.inflate(
                    R.layout.dialog_schedule_trade,
                    null
                )

                val builder = android.app.AlertDialog.Builder(this)
                builder.setView(dialogView)

                val dialog = builder.create()
                dialog.show()

                val datePicker = dialogView.findViewById<DatePicker>(R.id.datePicker)
                val timePicker = dialogView.findViewById<TimePicker>(R.id.timePicker)
                val btnConfirmTrade = dialogView.findViewById<Button>(R.id.btnConfirmTrade)

                btnConfirmTrade.setOnClickListener {
                    val day = datePicker.dayOfMonth
                    val month = datePicker.month + 1
                    val year = datePicker.year

                    val hour = timePicker.hour
                    val minute = timePicker.minute

                    //Just to show what a toast does look like and how to use it usefully
                    Toast.makeText(
                        this,
                        "Trade Scheduled for $day/$month/$year at $hour:$minute",
                        Toast.LENGTH_LONG
                    ).show()

                    dialog.dismiss()
                }
            }
        }

        // Navigation back to the home page
        btnHome.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }

        // Navigation back to the Books page
        btnBooks.setOnClickListener {
            startActivity(
                Intent(this, BooksActivity::class.java)
            )
        }

        //Navigation back to the Help page
        btnHelp.setOnClickListener {
            startActivity(
                Intent(this, HelpActivity::class.java)
            )
        }
    }
}