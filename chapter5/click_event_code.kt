package com.example.book

// Android framework imports
import android.os.Bundle
import androidx.core.view.WindowCompat

// Activity imports
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Compose UI imports
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Layout-specific imports
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi

/**
 * MainActivity serves as the entry point of the Android application.
 * It initializes the app's UI using Jetpack Compose and sets up the basic window configuration.
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
 * MainScreen is the root composable that organizes the app's content.
 * It displays three different examples of Compose UI components:
 * 1. ClickableTextExample - Demonstrates text click interaction
 * 2. ButtonClickExample - Shows button click counter functionality
 * 3. SelectableTagsExample - Displays interactive tag selection
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()  // Make the column take full width
            .padding(top = 50.dp)  // Add top margin for spacing from system UI
    ) {
        ClickableTextExample()
        Spacer(modifier = Modifier.height(32.dp))
        ButtonClickExample()
        Spacer(modifier = Modifier.height(32.dp))
        SelectableTagsExample()
    }
}

/**
 * ClickableTextExample demonstrates basic text interaction in Compose.
 * It shows a text that changes its content when clicked.
 * Uses remember and mutableStateOf to maintain the clicked state.
 */
@Composable
fun ClickableTextExample() {
    var clicked by remember { mutableStateOf(false) }

    Text(
        text = if (clicked) "You clicked the text!" else "Click this text",
        modifier = Modifier
            .padding(16.dp)
            .clickable { clicked = true }
    )
}

/**
 * ButtonClickExample demonstrates a simple counter functionality.
 * It shows a button that increments a counter when clicked and displays the current count.
 * Uses state management to track the number of clicks.
 */
@Composable
fun ButtonClickExample() {
    var count by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { count++ }) {
            Text("Add One")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("You clicked $count times")
    }
}

/**
 * SelectableTagsExample demonstrates the use of FlowRow and interactive tags.
 * Features:
 * - Displays a list of tags in a flowing layout
 * - Allows selection of a single tag
 * - Changes tag appearance based on selection state
 * - Shows the currently selected tag
 * 
 * Uses FlowRow for automatic wrapping of tags when they don't fit in one line.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectableTagsExample() {
    // State to track which tag is currently selected
    var selectedTag: String? by remember { mutableStateOf(null) }

    // List of available tags to display
    val tags = listOf("Kotlin", "Compose", "Android", "UI")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Select a tag:")
        Spacer(modifier = Modifier.height(8.dp))

        // FlowRow automatically wraps items to the next line when they don't fit
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            tags.forEach { tag ->
                val selected = tag == selectedTag
                Text(
                    text = tag,
                    modifier = Modifier
                        .background(
                            color = if (selected) Color.Blue else Color.LightGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedTag = tag }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    color = if (selected) Color.White else Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if (selectedTag != null)
                "Selected tag: $selectedTag"
            else
                "No tag selected"
        )
    }
}

/**
 * Preview composable for testing the UI in Android Studio's preview pane.
 * Shows the entire MainScreen with all examples.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}