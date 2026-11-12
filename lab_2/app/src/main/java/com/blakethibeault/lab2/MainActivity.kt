package com.blakethibeault.lab2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.blakethibeault.lab2.ui.theme.Lab2Theme
import com.blakethibeault.lab2.screenkeys.Detail
import com.blakethibeault.lab2.screenkeys.Library
import com.blakethibeault.lab2.screens.BookScreen
import com.blakethibeault.lab2.screens.DetailScreen
import com.blakethibeault.lab2.viewmodels.NavigationViewModel
import com.blakethibeault.lab2.viewmodels.CartViewModel

private const val TAG: String = "CartButton";

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                Surface(modifier = Modifier.fillMaxSize()) { // automatically gets surface color
                    LibraryApp()
                }

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
fun LibraryApp(
    navViewModel: NavigationViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel())
{
    NavDisplay(
        backStack = navViewModel.backStack,
        onBack = { navViewModel.goBack() },
        entryProvider = { key ->
            when (key) {
                is Library -> NavEntry(key) {
                    BookScreen(
                        library = ListOfBooks.books,
                        onBookClick = { book ->
                            navViewModel.openDetail(book.id)
                        },
                        // The library shows the same cart object the detail screen adds to.
                        viewModel = cartViewModel
                    )
                }
                is Detail -> NavEntry(key) {
                    DetailScreen(
                        bookId = key.itemId,
                        // This makes it so the actual logic of having the button be greyed out can still work
                        isInCart = cartViewModel.cart.any { it.id == key.itemId },
                        // Sending data back: nothing is "returned" from the detail screen.
                        // Instead, we update the shared state (the cart), then go back.
                        // When the library reappears, it reads the cart and shows the new item.
                        onAddToCart = { book ->
                            cartViewModel.addToCart(book)
                            navViewModel.goBack()
                        })
                }
                else -> NavEntry(key) {
                    Text(stringResource(R.string.unknown_screen))
                }
            }
        }
    )
}


