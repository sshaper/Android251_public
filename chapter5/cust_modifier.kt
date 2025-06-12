package com.example.book

// Android Framework Imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI Imports
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity serves as the entry point for the Android application.
 * This class demonstrates various custom modifier concepts including:
 * - Creating and using custom modifiers
 * - Parameterized custom modifiers
 * - Reusing modifier styles across components
 * - Combining custom modifiers with built-in ones
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
 * Custom modifier for creating tag-like elements with consistent styling.
 * This modifier adds a light gray background and padding to create a tag appearance.
 * 
 * @return Modifier with tag styling applied
 */
fun Modifier.tagStyle(): Modifier {
    return this
        .background(Color.LightGray)
        .padding(horizontal = 8.dp, vertical = 4.dp)
}

/**
 * Parameterized custom modifier for creating tag-like elements with customizable colors.
 * This modifier allows for custom background colors while maintaining consistent padding.
 * 
 * @param color The background color for the tag (defaults to LightGray)
 * @return Modifier with customizable tag styling applied
 */
fun Modifier.tagStyle(color: Color = Color.LightGray): Modifier {
    return this
        .background(color)
        .padding(horizontal = 8.dp, vertical = 4.dp)
}

/**
 * MainScreen is the root composable that serves as the main container for the app's content.
 * It demonstrates various uses of custom modifiers through different examples.
 * 
 * Layout Structure:
 * - Column: Main container with full width and top padding
 *   - BasicTagExample: Simple tag styling demonstration
 *   - Spacer: Fixed height spacing between examples
 *   - ColoredTagExample: Parameterized tag styling
 *   - Spacer: Fixed height spacing between examples
 *   - MixedTagExample: Combining custom and built-in modifiers
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        BasicTagExample()
        Spacer(modifier = Modifier.height(16.dp))
        ColoredTagExample()
        Spacer(modifier = Modifier.height(16.dp))
        MixedTagExample()
    }
}

/**
 * BasicTagExample demonstrates the use of a simple custom modifier.
 * 
 * This example shows:
 * - How to apply a basic custom modifier
 * - Creating a row of consistently styled tags
 * - Using Arrangement for spacing between tags
 */
@Composable
fun BasicTagExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Compose", modifier = Modifier.tagStyle())
        Text("Kotlin", modifier = Modifier.tagStyle())
        Text("UI", modifier = Modifier.tagStyle())
    }
}

/**
 * ColoredTagExample demonstrates the use of parameterized custom modifiers.
 * 
 * This example shows:
 * - How to use custom modifiers with parameters
 * - Creating tags with different background colors
 * - Using default parameter values
 */
@Composable
fun ColoredTagExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Android", modifier = Modifier.tagStyle(Color.Green))
        Text("iOS", modifier = Modifier.tagStyle())  // Uses default color
        Text("Web", modifier = Modifier.tagStyle(Color.Blue))
    }
}

/**
 * MixedTagExample demonstrates combining custom modifiers with built-in ones.
 * 
 * This example shows:
 * - How to chain custom modifiers with built-in ones
 * - Creating more complex styling combinations
 * - Using multiple custom modifiers together
 */
@Composable
fun MixedTagExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "New",
            modifier = Modifier
                .tagStyle(Color.Red)
                .padding(4.dp)  // Additional padding
        )
        Text(
            "Popular",
            modifier = Modifier
                .tagStyle(Color.Cyan)
                .padding(4.dp)  // Additional padding
        )
    }
}

/**
 * Preview function for development purposes.
 * This allows developers to see the UI in Android Studio's preview pane
 * without running the app on a device or emulator.
 * 
 * Parameters:
 * - showBackground: Enables background in preview
 * - showSystemUi: Shows system UI elements in preview
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCustomModifiers() {
    MainScreen()
}
