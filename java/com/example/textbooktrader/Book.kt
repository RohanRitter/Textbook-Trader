package com.example.textbooktrader

//Stores the book details
data class Book(
    val bookName: String,
    val isbn: String,
    val author: String,
    val school: String,
    val module: String,
    val condition: String,
    val price: String,
    val imageUri: String
)
