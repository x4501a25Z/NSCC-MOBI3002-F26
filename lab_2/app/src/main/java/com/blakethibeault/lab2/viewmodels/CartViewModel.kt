package com.blakethibeault.lab2.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.blakethibeault.lab2.Book
import kotlin.collections.plus

class CartViewModel : ViewModel() {
    var cart by mutableStateOf(listOf<Book>())
    private set

    fun addToCart(book: Book) {
        Log.d(ContentValues.TAG, "${book.title} added to cart")
        val isInCart : Boolean = false
        cart = cart + book;
    }

    fun clearCart() {
        cart = emptyList();
    }

}