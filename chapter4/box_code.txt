package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Theme imports
import com.example.book.ui.theme.BookTheme

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
 * 
 * The Column:
 * - Takes up the full width
 * - Has top padding of 50.dp
 * - Contains examples of different Box layouts:
 *   1. Basic Box with top and bottom text (BoxExample)
 *   2. Box with background color and centered text (ColoredBox)
 *   3. Box with overlay text element (BoxWithOverlay)
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            // Display different Box layout examples with spacing between them
            BoxExample()  // Basic box with top and bottom text
            Spacer(modifier = Modifier.height(16.dp))
            ColoredBox()  // Box with background color and centered text
            Spacer(modifier = Modifier.height(16.dp))
            BoxWithOverlay()  // Box with an overlay text element
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * BoxExample demonstrates basic Box layout with multiple elements.
 * Shows how to position elements at different locations within the box.
 * 
 * Features:
 * - Fixed size of 150x150dp
 * - 16.dp padding around the box
 * - Blue border of 1.dp
 * - Text elements positioned at:
 *   - Top center
 *   - Bottom center
 */
@Composable
fun BoxExample() {
    Box(modifier = Modifier
        .size(150.dp)
        .padding(16.dp)
        .border(width = 1.dp, color = Color.Blue)
    ) {
        Text(
            text = "Top Text",
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = "Bottom Text",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * ColoredBox demonstrates how to add background color to a Box.
 * Shows how to center content within a colored container.
 * 
 * Features:
 * - Fixed size of 200x200dp
 * - 16.dp padding around the box
 * - Rounded corners with 24.dp radius
 * - Deep purple background color (0xFF673AB7)
 * - White text content
 * - Subtle border in darker purple (0xFF512DA8)
 */
@Composable
fun ColoredBox() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)
            .background(
                Color(0xFF673AB7),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFF512DA8),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
            )
    ) {
        Text(
            "Box Content",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/**
 * BoxWithOverlay demonstrates how to create an overlay effect.
 * Shows how to position elements with background color and padding.
 * 
 * Features:
 * - Fixed size of 200x200dp
 * - 16.dp padding around the box
 * - Cyan background color
 * - Overlay text with:
 *   - White text color
 *   - Dark gray background
 *   - 4.dp padding
 *   - Positioned at bottom-right corner
 */
@Composable
fun BoxWithOverlay() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)
            .background(Color.Cyan)
    ) {
        Text(
            text = "Overlay",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Color.DarkGray)
                .padding(4.dp)
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
