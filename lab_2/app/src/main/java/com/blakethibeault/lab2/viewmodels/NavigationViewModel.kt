package com.blakethibeault.lab2.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.blakethibeault.lab2.screenkeys.Detail
import com.blakethibeault.lab2.screenkeys.Library

class NavigationViewModel: ViewModel() {

    var backStack by mutableStateOf(listOf<Any>(Library))
        private set

    fun openDetail(itemId: Int) {
        backStack = backStack + Detail(itemId)
        Log.d(TAG, "Back stack: $backStack")
    }

    fun goBack() {
        if (backStack.size > 1) {
            backStack = backStack.dropLast(1)
        }
        Log.d(TAG, "Back stack: $backStack")
    }

    companion object {
        const val TAG: String = "Navigation"
    }
}