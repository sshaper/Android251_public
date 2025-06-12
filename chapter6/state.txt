package com.example.book

// Android framework imports
import android.os.Bundle
import androidx.core.view.WindowCompat

// Activity imports
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Compose UI imports
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * MainActivity serves as the entry point of the Android application.
 * It initializes the app's UI using Jetpack Compose and configures the window appearance.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure the status bar to use dark icons for better visibility
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        // Set up the Compose UI with MaterialTheme
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

/**
 * MainScreen is the root composable that serves as the container for the app's content.
 * It provides the basic layout structure and spacing for the app's UI elements.
 * 
 * @param modifier Optional Modifier to customize the layout behavior
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()  // Make the column take full width
            .padding(top = 50.dp)  // Add top margin for spacing from system UI
    ) {
        CounterApp()
    }
}

/**
 * CounterApp is a composable that implements a simple counter application.
 * Features:
 * - Displays the current count
 * - Provides buttons to increment and decrement the count
 * - Uses state management to maintain the counter value
 * - Centers all content for better visual appearance
 */
@Composable
fun CounterApp() {
    // State management for the counter value
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the current count with custom text styling
        Text(
            text = "Count: $count",
            style = TextStyle(fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row containing the increment and decrement buttons
        Row {
            // Decrement button
            Button(onClick = { count-- }) {
                Text("-")
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Increment button
            Button(onClick = { count++ }) {
                Text("+")
            }
        }
    }
}

/**
 * Preview composable for testing the UI in Android Studio's preview pane.
 * Shows the entire MainScreen with the counter application.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}
