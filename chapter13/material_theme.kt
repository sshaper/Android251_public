package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                    CustomThemeExample()
                }
            }
        }
    }
}

/**
 * CustomThemeExample demonstrates how to create and apply a custom Material Design theme.
 * This composable shows:
 * 1. Custom color scheme definition using lightColorScheme
 * 2. Application of custom colors to Material components
 * 3. Proper use of Material Design typography
 * 
 * The custom theme uses:
 * - Primary color: Deep Purple (0xFF6200EE)
 * - Secondary color: Teal (0xFF03DAC6)
 * 
 * The layout demonstrates:
 * - Centered content using Column with Arrangement.Center
 * - Proper use of Material Design color system
 * - Typography styles from Material Design
 */
@Composable
fun CustomThemeExample() {
    // Define custom colors for the Material theme
    val customColors = lightColorScheme(
        primary = Color(0xFF6200EE),    // Deep Purple
        secondary = Color(0xFF03DAC6)   // Teal
    )

    // Apply the custom color scheme to the Material theme
    MaterialTheme(
        colorScheme = customColors
    ) {
        // Main content column with centered alignment
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Welcome text using Material Design typography and primary color
            Text(
                text = "Welcome!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
            
            // Primary action button using the theme's primary color
            Button(onClick = { /* TODO: Implement button action */ }) {
                Text("Primary Action")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Do something else */ }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text("Secondary Action (using secondary color)")
            }
        }
    }
}

/**
 * Preview composable for the CustomThemeExample.
 * This allows viewing the UI in Android Studio's preview pane without running the app.
 * showBackground and showSystemUi parameters enable a realistic preview with system UI elements.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        CustomThemeExample()
    }
}