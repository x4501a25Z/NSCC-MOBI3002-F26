package com.blakethibeault.semesterproject

import android.os.Bundle
import android.util.Log // Had to import this too for step 4
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column // This was added to be able to use 'Column'
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.blakethibeault.semesterproject.ui.theme.SemesterProjectTheme
// TODO: update this import to match your own project's generated theme
import com.blakethibeault.semesterproject.ui.theme.SemesterProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO: replace YourAppTheme with your project's generated theme name
            SemesterProjectTheme() {
                Greeting()
            }
        }
    }
}

private const val TAG = "SemesterProject" // I need a TAG val to know where this log message is coming from
                                          // // This is kinda pointless after refactoring
@Composable
fun Greeting() {
    Column {
//        val context = LocalContext.current

        Text(
            stringResource(R.string.my_greeting)
        )

        ReusableButton("Say Hello", "Hi") // Refactored first button
        ReusableButton("Click for $1", "I'm lying, my fingers were crossed")




//        Button(onClick = { // This toast message doesn't work because .show() was not called
//            Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show()
//            // I have to add a log using log.d to send a log message to Logcat
//            Log.d(TAG, "Say Hello button clicked")
//        }) {
//            Text("Say Hello")
//        }
//
//        Button(onClick = {
//            Toast.makeText(context, "I'm lying, my fingers were crossed", Toast.LENGTH_SHORT).show()
//            Log.d(TAG, "Click for $1 button clicked") // Don't know if this is necessary, but
//                                                            // I don't think it hurts to have
//        }) {
//            Text("Click for $1")
//        }
    }

}

@Composable
fun ReusableButton(buttonText : String, toastMessage : String) {
    val context = LocalContext.current

    Button(onClick = {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }) {
        Text(buttonText)
    }
}

// I didn't see anything about commits between progress, but it feels wrong not to document anything,
// so I'm gonna document the time I finish everything down here. Every time stamp will be from 10/04/2026
// -------------------------------------------------------------------------------------------------
// 7:57pm - Ran the program to confirm my replaced values are correct and the app can compile and run
// 1. Use a stringResource for the greeting. - Completed at 8:15pm
// 2. Fix the layout. - Completed at 8:21pm
// 3. Fix the broken Toast. - Completed at 8:29
// 4. Add logging that shows up on Logcat - Completed at 8:40pm
// 5. Add a second button. - Completed at 8:46pm
// 6. Extract a reusable composable. - Completed at 9:21pm
