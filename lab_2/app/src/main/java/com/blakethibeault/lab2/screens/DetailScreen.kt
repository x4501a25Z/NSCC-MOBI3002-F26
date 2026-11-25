package com.blakethibeault.lab2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.blakethibeault.lab2.Book
import com.blakethibeault.lab2.ListOfBooks
import com.blakethibeault.lab2.screens.BookScreen
import com.blakethibeault.lab2.R

@Composable
fun DetailScreen(bookId: Int, isInCart: Boolean , onAddToCart: (Book) -> Unit) {
    val book = ListOfBooks.books.find { it.id == bookId }

    if (book == null) {
        Text(stringResource(R.string.unknown_item))
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = book.title, style = MaterialTheme.typography.titleLarge)
        Text(text = book.author, style = MaterialTheme.typography.bodyLarge)
        Text(text = book.genre, style = MaterialTheme.typography.titleLarge,)
        // Reports the event, with this screen's book, up to Library.
        Button(onClick = { onAddToCart(book) }, enabled = !isInCart
        )
        {
            Text(if (isInCart) stringResource(R.string.already_in_cart_button) else stringResource(R.string.add_to_cart_button), color = MaterialTheme.colorScheme.onSurface)
        }
        }
    }
