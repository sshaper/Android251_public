package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity is the entry point of the application.
 * It sets up the basic window configuration and initializes the Compose UI.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure the window to use light status bar icons for better visibility
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        
        // Set up the Compose UI with Material Design theme
        setContent {
            MaterialTheme {
                // Surface provides a background color and elevation for the content
                Surface {
                    MaterialExample()
                }
            }
        }
    }
}

/**
 * MaterialExample is the main composable that demonstrates Material Design 3 components.
 * It uses Scaffold to create a basic app structure with a top app bar and content area.
 * 
 * The composable includes:
 * - A TopAppBar with the app title
 * - A Card component with elevation and padding
 * - A Spacer for vertical spacing
 * - A Button component
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialExample() {
    MaterialTheme {
        // Scaffold provides a basic Material Design layout structure
        Scaffold(
            // TopAppBar displays the app title at the top of the screen
            topBar = {
                TopAppBar(title = { Text("My App") })
            }
        ) { padding ->
            // Column arranges its children vertically
            Column(modifier = Modifier.padding(padding)) {
                // Card provides a surface with elevation and rounded corners
                Card(
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Text(
                        "Hello, Material Design!",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                // Spacer adds vertical space between components
                Spacer(modifier = Modifier.height(16.dp))
                // Button provides a clickable surface with ripple effect
                Button(onClick = { /* TODO: Implement button action */ }) {
                    Text("Click Me")
                }
            }
        }
    }
}

/**
 * Preview composable for the MaterialExample.
 * This allows viewing the UI in Android Studio's preview pane without running the app.
 * showBackground and showSystemUi parameters enable a realistic preview with system UI elements.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        MaterialExample()
    }
}