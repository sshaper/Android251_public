package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Experimental API imports
import androidx.compose.foundation.layout.ExperimentalLayoutApi

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
 * - Contains examples of different flow layouts:
 *   1. Dynamic FlowRow example
 *   2. Dynamic FlowColumn example
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            // Display the FlowRow example
            DynamicFlowRow()
            // Add spacing between examples
            Spacer(modifier = Modifier.height(32.dp))
            // Display the FlowColumn example
            DynamicFlowColumn()
        }
    }
}

/**
 * DynamicFlowRow demonstrates the FlowRow layout which automatically wraps items
 * to new rows when they exceed the available width.
 * 
 * Features:
 * - Dynamic item count controlled by a button
 * - Items arranged horizontally with wrapping
 * - 8.dp spacing between items and rows
 * - Light gray background for items
 * - Padding inside each item
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DynamicFlowRow() {
    // State to track number of items
    var itemCount by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Button to add more items
        Button(onClick = { itemCount++ }) {
            Text("Add Row Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // FlowRow automatically wraps items to new rows when they exceed the width
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),  // Space between items horizontally
            verticalArrangement = Arrangement.spacedBy(8.dp)     // Space between rows
        ) {
            // Create items based on itemCount
            for (i in 1..itemCount) {
                Text(
                    "Item $i",
                    modifier = Modifier
                        .background(Color.LightGray)  // Light gray background for visibility
                        .padding(horizontal = 12.dp, vertical = 8.dp)  // Padding inside each item
                )
            }
        }
    }
}

/**
 * DynamicFlowColumn demonstrates the FlowColumn layout which automatically wraps items
 * to new columns when they exceed the available height.
 * 
 * Features:
 * - Dynamic item count controlled by a button
 * - Items arranged vertically with wrapping
 * - 8.dp spacing between items and columns
 * - Cyan background for items
 * - Fixed height container (300.dp)
 * - Light gray background for the container
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DynamicFlowColumn() {
    // State to track number of items
    var itemCount by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Button to add more items
        Button(onClick = { itemCount++ }) {
            Text("Add Column Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Box with fixed height to demonstrate flowing behavior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)  // Fixed height container
                .background(Color.LightGray.copy(alpha = 0.2f))  // Light background to show container
                .padding(16.dp)
        ) {
            // FlowColumn automatically wraps items to new columns when they exceed the height
            FlowColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),    // Space between items vertically
                horizontalArrangement = Arrangement.spacedBy(8.dp)   // Space between columns
            ) {
                // Create items based on itemCount
                for (i in 1..itemCount) {
                    Text(
                        "Column $i",
                        modifier = Modifier
                            .background(Color.Cyan)  // Cyan background for visibility
                            .padding(horizontal = 12.dp, vertical = 8.dp)  // Padding inside each item
                    )
                }
            }
        }
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
