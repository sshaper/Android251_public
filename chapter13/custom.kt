package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity is the entry point of the application.
 * It sets up the basic window configuration and initializes the Compose UI.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure the window to use light status bar icons for better visibility
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        
        // Set up the Compose UI with Material Design theme
        setContent {
            MaterialTheme {
                // Surface provides a background color and elevation for the content
                Surface {
                    CustomButtonExample()
                }
            }
        }
    }
}

/**
 * CustomButtonExample demonstrates advanced Material Design 3 button customization:
 * 1. Custom button styling with specific colors
 * 2. Custom shape using RoundedCornerShape
 * 3. Custom elevation for depth
 * 4. Custom text color
 * 
 * The button features:
 * - Red background color (using Color.Red)
 * - Fully rounded corners (50dp radius)
 * - Elevated appearance (12dp elevation)
 * - White text color for contrast
 * - Top padding of 50dp for proper positioning
 * 
 * This example shows how to:
 * - Override default Material Design button colors
 * - Create custom button shapes
 * - Apply custom elevation
 * - Ensure proper text contrast
 */
@Composable
fun CustomButtonExample() {
    Button(
        // Add top padding to position the button
        modifier = Modifier.padding(top = 50.dp),
        // TODO: Implement button action
        onClick = { /* Do something */ },
        // Customize button colors
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        ),
        // Create a fully rounded button shape
        shape = RoundedCornerShape(50),
        // Add elevation for depth
        elevation = ButtonDefaults.buttonElevation(12.dp)
    ) {
        // Button text with custom color for contrast
        Text(
            "Danger!",
            color = Color.White
        )
    }
}

/**
 * Preview composable for the CustomButtonExample.
 * This allows viewing the UI in Android Studio's preview pane without running the app.
 * showBackground and showSystemUi parameters enable a realistic preview with system UI elements.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        CustomButtonExample()
    }
}