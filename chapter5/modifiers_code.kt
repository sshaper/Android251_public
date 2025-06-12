package com.example.book

// Android Framework Imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI Imports
import androidx.compose.foundation.background
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

/**
 * MainActivity serves as the entry point for the Android application.
 * This class demonstrates various Jetpack Compose UI concepts including:
 * - Modifier usage and chaining
 * - Layout composition
 * - Background and padding effects
 * - Parent-child relationships in layouts
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
 * It demonstrates a vertical layout using Column with multiple examples of modifier usage.
 * 
 * Layout Structure:
 * - Column: Main container with full width and top padding
 *   - ModifierExample: Basic padding and background demonstration
 *   - Spacer: Fixed height spacing between examples
 *   - ModifierSizeExample: Size constraints and alignment
 *   - ModifierChainExample: Modifier order importance
 *   - ModifierParentChildExample: Parent-child modifier relationships
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        ModifierExample()
        Spacer(modifier = Modifier.height(16.dp))
        ModifierSizeExample()
        Spacer(modifier = Modifier.height(16.dp))
        ModifierChainExample()
        Spacer(modifier = Modifier.height(16.dp))
        ModifierParentChildExample()
    }
}

/**
 * ModifierExample demonstrates basic modifier usage with padding and background.
 * 
 * This example shows:
 * - How to apply multiple modifiers in sequence
 * - The effect of nested padding (outer and inner)
 * - Background color application
 * - Text composable with modifiers
 */
@Composable
fun ModifierExample() {
    Text(
        text = "Hello, Modifier!",
        modifier = Modifier
            .padding(16.dp)  // Outer padding
            .background(Color.LightGray)  // Background color
            .padding(8.dp)  // Inner padding
    )
}

/**
 * ModifierSizeExample demonstrates size constraints and alignment in a Box layout.
 * 
 * This example shows:
 * - How to set fixed dimensions using size modifier
 * - Box layout with centered content
 * - Background color application to a container
 * - Text alignment within a Box
 */
@Composable
fun ModifierSizeExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.Cyan)
    ) {
        Text(
            text = "Centered",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/**
 * ModifierChainExample demonstrates the importance of modifier order.
 * 
 * This example shows:
 * - How modifier order affects the final appearance
 * - Background color application before padding
 * - The visual difference when modifiers are applied in different orders
 */
@Composable
fun ModifierChainExample() {
    Text(
        text = "Order Matters",
        modifier = Modifier
            .background(Color.Red)
            .padding(16.dp)
    )
}

/**
 * ModifierParentChildExample demonstrates parent-child modifier relationships.
 * 
 * This example shows:
 * - How modifiers can be applied to both parent and child components
 * - Column layout with background and padding
 * - Child elements with their own modifiers
 * - The visual hierarchy created by nested modifiers
 */
@Composable
fun ModifierParentChildExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        Text("Child 1", modifier = Modifier.background(Color.White).padding(8.dp))
        Text("Child 2", modifier = Modifier.background(Color.LightGray).padding(8.dp))
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
fun PreviewFlow() {
    MainScreen()
}
