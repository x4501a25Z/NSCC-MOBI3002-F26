package com.blakethibeault.lab2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.blakethibeault.lab2.ui.theme.Lab2Theme
import com.blakethibeault.lab2.R

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val genre: String
    )

val ListOfBooks = listOf<Book>(
    Book(1, "Dune", "Frank Herbert", "Science Fiction"),
    Book(2, "Neuromancer", "William Gibson", "Science Fiction"),
    Book(3, "Project Hail Mary", "Andy Weir", "Science Fiction"),
    Book(4, "The Three-Body Problem", "Cixin Liu", "Science Fiction"),
    Book(5, "To Kill A Mockingbird", "Harper Lee", "Classic"),
    Book(6, "1984", "George Orwell", "Classic"),
    Book(7, "Pride and Prejudice", "Jane Austen", "Classic"),
    Book(8, "The Great Gatsby", "F. Scott Fitzgerald", "Classic"),
    Book(9, "The Hobbit", "J.R.R Tolkien", "Fantasy"),
    Book(10, "A Game of Thrones", "George R.R Martin", "Fantasy"),
    Book(11, "The Lion, the witch and the Wardrobe", "C.S Lewis", "Fantasy"),
    Book(12, "The Way of Kings", "Bryan Sanderson", "Fantasy"),
)

private const val TAG: String = "CartButton";

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                BookScreen(ListOfBooks)
            }
        }
    }
}

@Composable
fun BookRow(book: Book, modifier: Modifier = Modifier) {
    val currentCtx = LocalContext.current;
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = book.title, style = MaterialTheme.typography.titleMedium)
            Text(text = book.author, style = MaterialTheme.typography.bodyMedium)
        }
        Button(onClick = {
            Log.d(TAG, "${book.title} added to cart")
            Toast.makeText(currentCtx, "Added ${book.title} to cart", Toast.LENGTH_SHORT).show()
        })
        {
            Text(stringResource(R.string.cart_button))
        }
    }
}

@Composable
fun BookScreen(books: List<Book>) {
    val sciFiBooks =  books.filter { it.genre == "Science Fiction"}
    val classicalBooks =  books.filter { it.genre == "Classic"}
    val fantasyBooks =  books.filter { it.genre == "Fantasy"}

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Science Fiction", style = MaterialTheme.typography.headlineSmall)
        }
        items (sciFiBooks, key = {it.id}) { book ->
            BookRow(book)
        }
        item {
            Text("Classic", style = MaterialTheme.typography.headlineSmall)
        }
        items(classicalBooks, key = { it.id}) { book ->
            BookRow(book)
        }
        item{
            Text("Fantasy", style = MaterialTheme.typography.headlineSmall)
        }
        items(fantasyBooks, key = { it.id}) { book ->
            BookRow(book)
        }
    }
}


