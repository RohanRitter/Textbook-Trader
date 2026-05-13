package com.example.textbooktrader

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class BooksActivity : AppCompatActivity() {

    // Here is our dummy books data
    val books = mutableListOf(

        Book(
            "Java Programming",
            "978-1234567890",
            "John Smith",
            "IT",
            "PRG512",
            "Good",
            "R250",
            ""
        ),

        Book(
            "Kotlin Basics",
            "978-0987654321",
            "Sarah Johnson",
            "IT",
            "KOT101",
            "Excellent",
            "R300",
            ""
        ),

        Book(
            "Database Systems",
            "978-1111111111",
            "Mike Brown",
            "Computer Science",
            "DBS401",
            "Fair",
            "R180",
            ""
        )

    )

    private var selectedImageUri: Uri? = null

    // Global image preview
    private lateinit var imgPreviewGlobal: ImageView

    // Image picker launcher
    private val imagePickerLauncher =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
                imgPreviewGlobal.setImageURI(uri)
                Toast.makeText(
                    this,
                    "Image Selected Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnAddBook = findViewById<Button>(R.id.btnAddBook)
        val btnInquiries = findViewById<Button>(R.id.btnInquiries)
        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnBooks = findViewById<Button>(R.id.btnBooks)
        val btnHelp = findViewById<Button>(R.id.btnHelp)

        // Display all the books
        displayBooks(books)

        // The Search button to show the Search Dialog
        btnSearch.setOnClickListener {

            val dialogView = layoutInflater.inflate(
                R.layout.dialog_search,
                null
            )

            val builder = android.app.AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()
            dialog.show()

            // The Search components
            val etSearchBook = dialogView.findViewById<EditText>(R.id.etSearchBook)
            val btnSearchNow = dialogView.findViewById<Button>(R.id.btnSearchNow)
            val spinnerSchool = dialogView.findViewById<Spinner>(R.id.spinnerSchool)
            val spinnerModule = dialogView.findViewById<Spinner>(R.id.spinnerModule)
            val btnFilterNow = dialogView.findViewById<Button>(R.id.btnFilterNow)

            // The School spinner array
            val schools = arrayOf(
                "All Schools",
                "IT",
                "Computer Science"
            )

            val schoolAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                schools
            )

            schoolAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )

            spinnerSchool.adapter = schoolAdapter

            // The Module Spinner
            val modules = arrayOf(
                "All Modules",
                "PRG512",
                "KOT101",
                "DBS401"
            )

            val moduleAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                modules
            )

            moduleAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )

            spinnerModule.adapter = moduleAdapter

            // The Logic behind the search
            btnSearchNow.setOnClickListener {
                val searchText = etSearchBook.text.toString()
                val filteredBooks = books.filter {
                    it.bookName.contains(
                        searchText,
                        ignoreCase = true
                    )
                }
                displayBooks(filteredBooks)
                dialog.dismiss()
            }

            // Now for the logic of the filter system
            btnFilterNow.setOnClickListener {
                val selectedSchool = spinnerSchool.selectedItem.toString()
                val selectedModule = spinnerModule.selectedItem.toString()
                val filteredBooks = books.filter { book ->
                    val schoolMatches =
                        selectedSchool == "All Schools" ||
                                book.school == selectedSchool
                    val moduleMatches =
                        selectedModule == "All Modules" ||
                                book.module == selectedModule
                    schoolMatches && moduleMatches
                }
                displayBooks(filteredBooks)
                dialog.dismiss()
            }
        }

        // The Add Book Section
        btnAddBook.setOnClickListener {
            val dialogView = layoutInflater.inflate(
                R.layout.dialog_add_book,
                null
            )
            val builder = android.app.AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()
            dialog.show()

            // The input fields for the details of the book wanting to add
            val etBookName = dialogView.findViewById<EditText>(R.id.etBookName)
            val etISBN = dialogView.findViewById<EditText>(R.id.etISBN)
            val etAuthor = dialogView.findViewById<EditText>(R.id.etAuthor)
            val etSchool = dialogView.findViewById<EditText>(R.id.etSchool)
            val etModule = dialogView.findViewById<EditText>(R.id.etModule)
            val etCondition = dialogView.findViewById<EditText>(R.id.etCondition)
            val etPrice = dialogView.findViewById<EditText>(R.id.etPrice)
            val imgPreview = dialogView.findViewById<ImageView>(R.id.imgSmileShark)
            val btnUploadImage = dialogView.findViewById<Button>(R.id.btnUploadImage)
            val btnSaveBook = dialogView.findViewById<Button>(R.id.btnSaveBook)

            // Global preview image
            imgPreviewGlobal = imgPreview

            // The Image button to add an image for the book
            btnUploadImage.setOnClickListener {
                imagePickerLauncher.launch("image/*")
            }

            // Save book button to add the new book
            btnSaveBook.setOnClickListener {

                val bookName = etBookName.text.toString().trim()
                val isbn = etISBN.text.toString().trim()
                val author = etAuthor.text.toString().trim()
                val school = etSchool.text.toString().trim()
                val module = etModule.text.toString().trim()
                val condition = etCondition.text.toString().trim()
                val price = etPrice.text.toString().trim()

                // Validating to ensure data has been entered and correctly entered
                if (bookName.isEmpty()) {
                    etBookName.error =
                        "Enter Book Name"
                    return@setOnClickListener
                }

                if (isbn.isEmpty()) {
                    etISBN.error =
                        "Enter ISBN"
                    return@setOnClickListener
                }

                if (author.isEmpty()) {
                    etAuthor.error =
                        "Enter Author"
                    return@setOnClickListener
                }

                if (school.isEmpty()) {
                    etSchool.error =
                        "Enter School"
                    return@setOnClickListener
                }

                if (module.isEmpty()) {
                    etModule.error =
                        "Enter Module"
                    return@setOnClickListener
                }

                if (condition.isEmpty()) {
                    etCondition.error =
                        "Enter Condition"
                    return@setOnClickListener
                }

                if (price.isEmpty()) {
                    etPrice.error =
                        "Enter Price"
                    return@setOnClickListener
                }

                if (!price.startsWith("R")) {
                    etPrice.error =
                        "Price must start with R"
                    return@setOnClickListener
                }

                //Disable this if you dont have an image in your phone app to upload
                if (selectedImageUri == null) {
                    Toast.makeText(
                        this,
                        "Please Upload an Image",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                // Creates the book
                val newBook = Book(
                    bookName,
                    isbn,
                    author,
                    school,
                    module,
                    condition,
                    price,
                    selectedImageUri.toString()
                )
                books.add(newBook)
                displayBooks(books)

                Toast.makeText(
                    this,
                    "Book Added Successfully",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
        }

        // Button to the Inquiries page
        btnInquiries.setOnClickListener {
            val intent =
                Intent(this, InquiriesActivity::class.java)
            startActivity(intent)
        }

        // Button to the Home page
        btnHome.setOnClickListener {
            val intent =
                Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Reload the book page
        btnBooks.setOnClickListener {
            val intent =
                Intent(this, BooksActivity::class.java)
            startActivity(intent)
        }

        // The Help page button
        btnHelp.setOnClickListener {
            val intent =
                Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }

    // function to display the books
    private fun displayBooks(bookList: List<Book>) {
        val bookContainer =
            findViewById<LinearLayout>(R.id.bookContainer)
        bookContainer.removeAllViews()

        for (book in bookList) {
            val bookView = layoutInflater.inflate(
                R.layout.book_item,
                null
            )

            val imgBook = bookView.findViewById<ImageView>(R.id.imgBook)
            val txtBookName = bookView.findViewById<TextView>(R.id.txtBookName)
            val txtISBN = bookView.findViewById<TextView>(R.id.txtISBN)
            val txtAuthor = bookView.findViewById<TextView>(R.id.txtAuthor)
            val txtSchool = bookView.findViewById<TextView>(R.id.txtSchool)
            val txtModule = bookView.findViewById<TextView>(R.id.txtModule)
            val txtCondition = bookView.findViewById<TextView>(R.id.txtCondition)
            val txtPrice = bookView.findViewById<TextView>(R.id.txtPrice)

            // The image
            if (book.imageUri.isNotEmpty()) {
                imgBook.setImageURI(
                    Uri.parse(book.imageUri)
                )
            } else {
                imgBook.setImageResource(
                    R.mipmap.ic_launcher
                )
            }

            // The Text
            txtBookName.text = "Book: ${book.bookName}"
            txtISBN.text = "ISBN: ${book.isbn}"
            txtAuthor.text = "Author: ${book.author}"
            txtSchool.text = "School: ${book.school}"
            txtModule.text = "Module: ${book.module}"
            txtCondition.text = "Condition: ${book.condition}"
            txtPrice.text = "Price: ${book.price}"

            // Adding the card
            bookContainer.addView(bookView)

            // Adding the book to the inquiries
            bookView.setOnClickListener {

                BookRepository.inquiryBooks.add(book)

                Toast.makeText(
                    this,
                    "${book.bookName} added to inquiries",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}