package com.blakethibeault.lab2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blakethibeault.lab2.Book
import com.blakethibeault.lab2.viewmodels.CartViewModel
import com.blakethibeault.lab2.R

@Composable
fun BookRow(book: Book, onClick: () -> Unit, modifier: Modifier = Modifier, isInCart: Boolean) {
    val currentCtx = LocalContext.current;
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Title: " + book.title, style = MaterialTheme.typography.titleLarge)
            Text(text = "Author: " + book.author, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun BookScreen(
    library: List<Book>,
    onBookClick: (Book) -> Unit,
    viewModel: CartViewModel = viewModel()
) { // Requirement 2 Here
    // Extra space for code
    val sciFiBooks = library.filter { it.genre == "Science Fiction" }
    val classicalBooks = library.filter { it.genre == "Classic" }
    val fantasyBooks = library.filter { it.genre == "Fantasy" }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_novel),
                    contentDescription = stringResource(R.string.logo_description),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    stringResource(R.string.app_title),
                    style = MaterialTheme.typography.headlineSmall
                )
            }

        }
        // New Summary Item
        item {
            var isCartVisible by rememberSaveable { mutableStateOf(true) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            )
            {
                Text(
                    "Cart: " + viewModel.cart.size + " books",
                    style = MaterialTheme.typography.headlineSmall
                )
                Button(
                    onClick = { isCartVisible = !isCartVisible },
                    enabled = viewModel.cart.isNotEmpty()
                ) {
                    Text(
                        if (isCartVisible) stringResource(R.string.hide_cart_button) else stringResource(
                            R.string.show_cart_button
                        ), color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Button(
                    onClick = { viewModel.clearCart() },
                    enabled = viewModel.cart.isNotEmpty() // Automatically handles the true/false logic
                ) {
                    Text(
                        stringResource(R.string.clear_cart_button),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            if (isCartVisible) {
                viewModel.cart.forEach { book ->
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
//        items()
        item {
            Text(
                stringResource(R.string.scifi_genre),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        items(sciFiBooks, key = { it.id }) { book ->
            BookRow(book, isInCart = viewModel.cart.contains(book), onClick = { onBookClick(book) })
        }
        item {
            Text(
                stringResource(R.string.classic_genre),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        items(classicalBooks, key = { it.id }) { book ->
            BookRow(book, isInCart = viewModel.cart.contains(book), onClick = { onBookClick(book) })
        }
        item {
            Text(
                stringResource(R.string.fantasy_genre),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        items(fantasyBooks, key = { it.id }) { book ->
            BookRow(book, isInCart = viewModel.cart.contains(book), onClick = { onBookClick(book) })
        }
    }
}