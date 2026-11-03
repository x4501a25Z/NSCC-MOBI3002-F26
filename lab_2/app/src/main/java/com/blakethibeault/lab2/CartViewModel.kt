package com.blakethibeault.lab2

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    var cart by mutableStateOf(listOf<Book>())
    private set

    fun addToCart(book: Book) {
        Log.d(TAG, "${book.title} added to cart")
        val isInCart : Boolean = false
        cart = cart + book;
    }

    fun clearCart() {
        cart = emptyList();
    }

}