package com.blakethibeault.lab2

import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    Book(11, "The Lion, the Witch and the Wardrobe", "C.S Lewis", "Fantasy"),
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

}

@Composable
fun BookRow(book: Book, modifier: Modifier = Modifier, isInCart: Boolean, onAddToCart: () -> Unit) {
    val currentCtx = LocalContext.current;
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Title: " + book.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "Author: " + book.author, style = MaterialTheme.typography.bodyMedium)
        }
        Button(onClick = { onAddToCart() }, enabled = !isInCart
        )
        {
            Text(if (isInCart) "In Cart" else "Add to Cart")
        }
    }
}

@Composable
fun BookScreen(books: List<Book>, viewModel: CartViewModel = viewModel() ) { // Requirement 2 Here
    // Extra space for code
    val sciFiBooks =  books.filter { it.genre == "Science Fiction"}
    val classicalBooks =  books.filter { it.genre == "Classic"}
    val fantasyBooks =  books.filter { it.genre == "Fantasy"}

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {
        // New Summary Item
        item {
            var isCartVisible by rememberSaveable { mutableStateOf(true) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            )
            {
                Text("Cart: " + viewModel.cart.size + " books", style = MaterialTheme.typography.headlineSmall)
                Button(onClick = { isCartVisible = !isCartVisible }) {
                    Text(if (isCartVisible) "Hide" else "Show")
                }
                Button(
                    onClick = { viewModel.clearCart() },
                    enabled = viewModel.cart.isNotEmpty() // Automatically handles the true/false logic
                ) {
                    Text("Clear")
                }
            }
            if (isCartVisible) {
                viewModel.cart.forEach { book ->
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

        }
//        items()
        item {
            Text("Science Fiction", style = MaterialTheme.typography.headlineSmall)
        }
        items (sciFiBooks, key = {it.id}) { book ->
            BookRow(book, isInCart = viewModel.cart.contains(book), onAddToCart = { viewModel.addToCart(book) } )
        }
        item {
            Text("Classic", style = MaterialTheme.typography.headlineSmall)
        }
        items(classicalBooks, key = { it.id}) { book ->
            BookRow(book, isInCart = viewModel.cart.contains(book), onAddToCart = { viewModel.addToCart(book) })
        }
        item{
            Text("Fantasy", style = MaterialTheme.typography.headlineSmall)
        }
        items(fantasyBooks, key = { it.id}) { book ->
            BookRow(book, isInCart = viewModel.cart.contains(book), onAddToCart = { viewModel.addToCart(book) })
        }
    }
}


