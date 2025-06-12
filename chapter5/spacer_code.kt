package com.example.book

// Android Framework Imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI Imports
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity serves as the entry point for the Android application.
 * This class demonstrates the use of Jetpack Compose for building modern Android UIs.
 * 
 * Key features demonstrated:
 * - Edge-to-edge display implementation
 * - Material 3 theming
 * - Basic layout components (Box, Column)
 * - Spacing techniques (Spacer, Arrangement)
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configure the window to use light status bar icons for better visibility
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        
        // Set up the Compose UI with Material 3 theme
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

/**
 * MainScreen is the root composable that serves as the main container for the app's content.
 * It demonstrates a common layout pattern using Box and Column composables.
 * 
 * Layout Structure:
 * - Box: Fills the entire screen
 *   - Column: Arranges content vertically with padding from the top
 *     - SpacerExample: Demonstrates manual spacing
 *     - Spacer: Adds fixed spacing between examples
 *     - ArrangementExample: Demonstrates automatic spacing
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            SpacerExample()
            Spacer(modifier = Modifier.height(32.dp))
            ArrangementExample()
        }
    }
}

/**
 * SpacerExample demonstrates the use of Spacer composable for manual spacing control.
 * 
 * This example shows:
 * - How to add fixed amounts of space between elements
 * - Different spacing values (16dp and 32dp)
 * - Basic Column layout with padding
 */
@Composable
fun SpacerExample() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Item 1")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Item 2")
        Spacer(modifier = Modifier.height(32.dp))
        Text("Item 3")
    }
}

/**
 * ArrangementExample demonstrates an alternative approach to spacing using Arrangement.
 * 
 * This example shows:
 * - How to use Arrangement.spacedBy for automatic spacing
 * - The difference between manual Spacer and automatic spacing
 * - Column layout with full width and padding
 */
@Composable
fun ArrangementExample() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Item A")
        Text("Item B")
        Text("Item C")
    }
}

/**
 * Preview function for development purposes.
 * This allows developers to see the UI in Android Studio's preview pane
 * without running the app on a device or emulator.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}
