package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
 * The Box:
 * - Takes up the full available space
 * 
 * The Column:
 * - Takes up the full width
 * - Has top padding of 50.dp
 * - Contains examples of different Column layouts:
 *   1. Basic Column (ColumnExample)
 *   2. Centered Column (CenteredColumn)
 *   3. Spaced Column (SpacedColumn)
 *   4. Styled Column (StyledColumn)
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            // Display different column examples with spacing between them
            ColumnExample()  // Basic column with text and button
            Spacer(modifier = Modifier.padding(10.dp))  // Add vertical spacing
            CenteredColumn()  // Column with centered items
            Spacer(modifier = Modifier.padding(10.dp))  // Add vertical spacing
            SpacedColumn()    // Column with automatic spacing between items
            Spacer(modifier = Modifier.padding(10.dp))  // Add vertical spacing
            StyledColumn()    // Column with background colors and styling
        }
    }
}

/**
 * ColumnExample demonstrates a basic Column layout.
 * Shows how to stack elements vertically with basic padding.
 * 
 * Features:
 * - Basic vertical arrangement
 * - Simple padding
 * - Multiple text elements
 */
@Composable
fun ColumnExample() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Welcome to Compose!")  // First text element
        Text("Let's build a layout.") // Second text element
    }
}

/**
 * CenteredColumn demonstrates how to center items horizontally.
 * Uses horizontalAlignment parameter to center all children.
 * 
 * Features:
 * - Full width column
 * - Centered horizontal alignment
 * - Multiple centered text elements
 */
@Composable
fun CenteredColumn() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Centered Item 1")  // First centered text
        Text("Centered Item 2")  // Second centered text
    }
}

/**
 * SpacedColumn demonstrates automatic spacing between items.
 * Uses Arrangement.spacedBy to add consistent vertical spacing.
 * 
 * Features:
 * - Automatic vertical spacing
 * - Consistent padding
 * - Multiple spaced items
 */
@Composable
fun SpacedColumn() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Item One")    // First item
        Text("Item Two")    // Second item
        Text("Item Three")  // Third item
    }
}

/**
 * StyledColumn demonstrates various styling options for a Column layout.
 * This composable shows:
 * 1. Background color using MaterialTheme
 * 2. Rounded corners with RoundedCornerShape
 * 3. Border with primary color
 * 4. Custom text styling with different sizes and weights
 * 5. Different padding for different elements
 * 6. Nested Box with secondary styling
 * 
 * Features:
 * - Material theme integration
 * - Custom text styles
 * - Styled button
 * - Styled box with rounded corners
 * - Proper color contrast
 */
@Composable
fun StyledColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Styled Column",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "This is a styled text",
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        
        Button(
            onClick = { /* TODO: Add click action */ },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Styled Button")
        }
        
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Box",
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}
