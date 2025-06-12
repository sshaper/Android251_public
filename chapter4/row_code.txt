package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
 * 
 * The Column:
 * - Takes up the full width
 * - Has top padding of 50.dp
 * - Contains examples of different Row layouts:
 *   1. Basic Row with padding (RowExample)
 *   2. Row with explicit spacing (RowWithSpacer)
 *   3. Row with space-between arrangement (RowWithArrangement)
 *   4. Row with top alignment (RowTopAlignment)
 *   5. Row with center alignment (RowCenterAlignment)
 *   6. Row with bottom alignment (RowBottomAlignment)
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            // Display different Row layout examples with spacing between them
            RowExample()  // Basic row with padding from border
            Spacer(modifier = Modifier.height(16.dp))
            RowWithSpacer()  // Row with explicit spacing between elements
            Spacer(modifier = Modifier.height(16.dp))
            RowWithArrangement()  // Row with space-between arrangement
            Spacer(modifier = Modifier.height(16.dp))
            RowTopAlignment()  // Row with top vertical alignment
            Spacer(modifier = Modifier.height(16.dp))
            RowCenterAlignment()  // Row with center vertical alignment
            Spacer(modifier = Modifier.height(16.dp))
            RowBottomAlignment()  // Row with bottom vertical alignment
        }
    }
}

/**
 * RowExample demonstrates a basic Row layout with padding from the border.
 * Shows how to create space between the elements and the border of the Row.
 * 
 * Features:
 * - Outer padding of 16.dp
 * - Blue border of 1.dp
 * - Inner padding of 16.dp between elements and border
 * - Five text elements arranged horizontally
 */
@Composable
fun RowExample() {
    Row(
        modifier = Modifier
            .padding(16.dp)  // Outer padding
            .border(width = 1.dp, color = Color.Blue)
            .padding(16.dp)  // Inner padding between elements and border
    ) {
        Text("First")
        Text("Second")
        Text("Third")
        Text("Fourth")
        Text("Fifth")
    }
}

/**
 * RowWithSpacer demonstrates how to add explicit spacing between elements.
 * Uses Spacer composables to create fixed-width gaps between elements.
 * 
 * Features:
 * - Padding of 16.dp around the row
 * - Fixed 10.dp spacing between each element
 * - Five text elements with consistent spacing
 */
@Composable
fun RowWithSpacer() {
    Row(modifier = Modifier.padding(16.dp)) {
        Text("First")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Second")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Third")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Fourth")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Fifth")
    }
}

/**
 * RowWithArrangement demonstrates how to use Arrangement to space elements.
 * Uses SpaceBetween to push elements to opposite ends of the row.
 * 
 * Features:
 * - Full width row
 * - Padding of 16.dp around the row
 * - SpaceBetween arrangement for even distribution
 * - Five text elements spread across the width
 */
@Composable
fun RowWithArrangement() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("First")
        Text("Second")
        Text("Third")
        Text("Fourth")
        Text("Fifth")
    }
}

/**
 * RowTopAlignment demonstrates vertical alignment at the top.
 * Shows how to align elements to the top of a fixed-height row.
 * 
 * Features:
 * - Fixed height of 100.dp
 * - Full width
 * - Blue border
 * - Padding of 16.dp
 * - Top vertical alignment
 * - Fixed 16.dp spacing between elements
 */
@Composable
fun RowTopAlignment() {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Blue)
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text("First")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Second")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Third")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Fourth")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Fifth")
    }
}

/**
 * RowCenterAlignment demonstrates vertical alignment at the center.
 * Shows how to center elements vertically within a fixed-height row.
 * 
 * Features:
 * - Fixed height of 100.dp
 * - Full width
 * - Blue border
 * - Padding of 16.dp
 * - Center vertical alignment
 * - Fixed 16.dp spacing between elements
 */
@Composable
fun RowCenterAlignment() {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Blue)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("First")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Second")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Third")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Fourth")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Fifth")
    }
}

/**
 * RowBottomAlignment demonstrates vertical alignment at the bottom.
 * Shows how to align elements to the bottom of a fixed-height row.
 * 
 * Features:
 * - Fixed height of 100.dp
 * - Full width
 * - Blue border
 * - Padding of 16.dp
 * - Bottom vertical alignment
 * - Fixed 16.dp spacing between elements
 */
@Composable
fun RowBottomAlignment() {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Blue)
            .padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text("First")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Second")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Third")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Fourth")
        Spacer(modifier = Modifier.width(16.dp))
        Text("Fifth")
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