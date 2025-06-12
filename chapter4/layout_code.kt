package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
 * The Box:
 * - Takes up the full available space
 * - Has top padding of 50.dp
 * 
 * The Column:
 * - Takes up the full width
 * - Has top padding of 50.dp
 * - Contains labels and examples of different layouts
 */
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            // Label for Column example
            Text("Column Example")
            // Display the Column example with vertical arrangement
            ColumnExample()
            // Add vertical spacing between examples
            Spacer(modifier = Modifier.height(16.dp))
            
            // Label for Row example
            Text("Row Example")
            // Display the Row example with horizontal arrangement
            RowExample()
            // Add vertical spacing between examples
            Spacer(modifier = Modifier.height(16.dp))
            
            // Label for Box example
            Text("Box Example")
            // Display the Box example with stacking arrangement
            BoxExample()
        }
    }
}

/**
 * ColumnExample demonstrates vertical arrangement of elements.
 * Elements are stacked from top to bottom.
 * 
 * The Column:
 * - Has outer padding of 16.dp
 * - Has a secondary-colored border of 2.dp
 * - Contains three Text elements arranged vertically
 */
@Composable
fun ColumnExample() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, MaterialTheme.colorScheme.secondary)
    ) {
        Text("Part 1")
        Text("Part 2")
        Text("Part 3")
        Text("Part 4")
        Text("Part 5")
    }
}

/**
 * RowExample demonstrates horizontal arrangement of elements.
 * Elements are placed side by side from left to right.
 * 
 * The Row:
 * - Has outer padding of 16.dp
 * - Has a tertiary-colored border of 2.dp
 * - Contains five Text elements with Spacers between them
 */
@Composable
fun RowExample() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, MaterialTheme.colorScheme.tertiary)
    ) {
        Text("Part 1")
        Spacer(modifier = Modifier.width(20.dp))
        Text("Part 2")
        Spacer(modifier = Modifier.width(20.dp))
        Text("Part 3")
        Spacer(modifier = Modifier.width(20.dp))
        Text("Part 4")
        Spacer(modifier = Modifier.width(20.dp))
        Text("Part 5")
    }
}

/**
 * BoxExample demonstrates stacking arrangement of elements.
 * Elements can be positioned using Alignment.
 * 
 * The Box:
 * - Has a fixed size of 300.dp
 * - Has outer padding of 16.dp
 * - Has a primary-colored border of 2.dp
 * - Contains three Text elements positioned at different alignments:
 *   - Top center
 *   - Center
 *   - Bottom center
 */
@Composable
fun BoxExample() {
    Box(
        modifier = Modifier
            .size(300.dp)
            .padding(16.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = "This is the top part of a box",
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = "This is the middle part of a box",
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = "This is the bottom part of a box",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
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