package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        // Configure the window to use light status bar icons for better visibility
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        
        // Set up the Compose UI with Material Design theme
        setContent {
            MaterialTheme {
                Surface {
                    CounterParent()  // Main content of the app
                }
            }
        }
    }
}

/**
 * CounterParent is the main composable that manages the counter state.
 * It follows the state hoisting pattern where:
 * 1. State is managed at the parent level
 * 2. State is passed down to child composables
 * 3. Events are passed up through callbacks
 * 
 * The composable uses:
 * - remember and mutableStateOf for state management
 * - Column for vertical layout
 * - Modifier.padding for proper spacing
 */
@Composable
fun CounterParent() {
    // State management using remember and mutableStateOf
    // This ensures the state persists across recompositions
    var count by remember { mutableStateOf(0) }
    
    // Column layout with padding for proper spacing
    Column(
        modifier = Modifier
            .padding(top = 50.dp)  // Space from top of screen
            .padding(16.dp)        // Padding around all sides
    ) {
        // Child composables receive state and callbacks as parameters
        CounterDisplay(count = count)
        CounterButton(onIncrement = { count++ })
    }
}

/**
 * CounterDisplay is a stateless composable that shows the current count.
 * It receives the count as a parameter and simply displays it.
 * Being stateless makes it reusable and easier to test.
 */
@Composable
fun CounterDisplay(count: Int) {
    Text("Count: $count")
}

/**
 * CounterButton is a stateless composable that provides the increment functionality.
 * It receives a callback function (onIncrement) as a parameter.
 * When clicked, it triggers the callback to update the parent's state.
 */
@Composable
fun CounterButton(onIncrement: () -> Unit) {
    Button(onClick = onIncrement) {
        Text("Increment")
    }
}

/**
 * Preview function for the CounterParent composable.
 * This allows us to see how the UI looks in Android Studio's preview pane
 * without having to run the app.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CounterParentPreview() {
    MaterialTheme {
        CounterParent()
    }
}