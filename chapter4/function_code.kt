package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



/**
 * MainActivity is the entry point of the Android application.
 * It extends ComponentActivity which provides the basic Android activity functionality.
 * This class is responsible for:
 * 1. Setting up the window configuration
 * 2. Initializing the Compose UI
 * 3. Managing the activity lifecycle
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
 * MainScreen is the root composable of the application.
 * It uses a Box as a container to potentially layer UI elements.
 * Inside the Box, it uses a Column to arrange its children vertically.
 * 
 * The Column:
 * - Takes up the full available space
 * - Has padding of 50.dp on all sides
 * - Centers its children horizontally
 */
@Composable
fun MainScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Demonstrates different ways to use the Greeting composable:
            Greeting()  // Uses default values
            Greeting(name = "Android")  // Custom name
            Greeting(
                name = "Developer",
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

/**
 * Greeting is a reusable composable that displays a customizable text message.
 * It demonstrates several Compose best practices:
 * 1. Optional parameters with default values
 * 2. Modifier parameter for styling
 * 3. Theming using MaterialTheme
 * 
 * @param name The name to display in the greeting (defaults to "Compose")
 * @param modifier Optional modifier for styling and layout
 */
@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    name: String = "Compose"
    
) {
    Text(
        text = "Hello, $name!",
        modifier = modifier.padding(16.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        color = MaterialTheme.colorScheme.primary
    )
}

/**
 * Preview function for Android Studio's preview pane.
 * This allows developers to see the UI without running the app.
 * 
 * @param showBackground Shows a background in the preview
 * @param showSystemUi Shows the system UI in the preview
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}