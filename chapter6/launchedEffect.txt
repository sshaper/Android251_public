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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Coroutines imports
import kotlinx.coroutines.delay

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
        LoadingScreen()
    }
}

/**
 * LoadingScreen demonstrates the use of LaunchedEffect for handling asynchronous operations.
 * It shows a loading spinner while simulating data loading and displays a success message when complete.
 * 
 * Key Features:
 * 1. Uses LaunchedEffect for side effects (data loading)
 * 2. Implements loading state management
 * 3. Shows different UI states (loading vs. loaded)
 * 4. Demonstrates proper coroutine usage in Compose
 * 
 * State Variables:
 * - isLoading: Controls the visibility of the loading spinner
 * - message: Stores the success message to display after loading
 */
@Composable
fun LoadingScreen() {
    // State management for loading status
    var isLoading by remember { mutableStateOf(true) }

    // State management for the loaded message
    var message by remember { mutableStateOf("") }

    // LaunchedEffect for handling the data loading operation
    // The Unit parameter ensures this effect only runs once when the composable is first created
    LaunchedEffect(Unit) {
        // Simulate network delay or data processing
        delay(9000) // 9 second delay to demonstrate loading state
        message = "Data loaded successfully!"
        isLoading = false
    }

    // Main UI layout
    Column(
        modifier = Modifier
            .fillMaxSize()  // Take up all available space
            .padding(16.dp),  // Add padding around the edges
        horizontalAlignment = Alignment.CenterHorizontally,  // Center items horizontally
        verticalArrangement = Arrangement.Center  // Center items vertically
    ) {
        if (isLoading) {
            // Loading state UI
            CircularProgressIndicator()  // Loading spinner
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            // Loaded state UI
            Text(
                text = message,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

/**
 * Preview composable for testing the UI in Android Studio's preview pane.
 * Shows the loading screen with its initial state.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}
