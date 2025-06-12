package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel

// Compose UI imports
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity serves as the entry point of the application.
 * It's responsible for:
 * 1. Setting up the basic window configuration
 * 2. Initializing the Compose UI
 * 3. Applying the Material Design theme
 * 4. Managing the application's main surface
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configure the window to use light status bar icons
        // This ensures better visibility of status bar icons on light backgrounds
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        
        // Set up the Compose UI with Material Design theme
        setContent {
            // MaterialTheme provides the app's color scheme, typography, and shape definitions
            MaterialTheme {
                // Surface provides a background color and elevation for the content
                // It's a basic building block that follows Material Design guidelines
                Surface {
                    // CounterScreen is the main content of our app
                    CounterScreen()
                }
            }
        }
    }
}

/**
 * CounterScreen is a composable function that displays a counter interface.
 * It consists of:
 * 1. A button to increment the counter
 * 2. A text display showing the current count
 * 
 * The screen uses a ViewModel to maintain state across recompositions and configuration changes.
 * The layout is arranged in a Column with proper padding for better visual appearance.
 */
@Composable
fun CounterScreen() {
    // Get or create a ViewModel instance
    // viewModel() is a composable function that handles ViewModel lifecycle
    val viewModel: MainViewModel = viewModel()
    
    // Column arranges its children vertically
    Column(
        modifier = Modifier
            .padding(top = 50.dp)  // Add top padding for better spacing from status bar
            .padding(16.dp)        // Add padding around all sides
    ) {
        // Button that triggers the increment function in ViewModel
        Button(onClick = { viewModel.increment() }) {
            Text("Increment")
        }
        
        // Text composable that displays the current count
        // It automatically updates when the count changes in ViewModel
        Text("Count: ${viewModel.count}")
    }
}

/**
 * Preview function for the CounterScreen
 * This allows us to see how the screen looks in Android Studio's preview pane
 * without having to run the app
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimatedCardPreview() {
    MaterialTheme {
        CounterScreen()
    }
}