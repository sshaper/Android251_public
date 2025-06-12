package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
                    LightDarkModeExample()
                }
            }
        }
    }
}

/**
 * LightDarkModeExample demonstrates Material Design 3's dynamic theming capabilities:
 * 1. Automatic theme switching based on system settings
 * 2. Dynamic color scheme application
 * 3. Proper use of Material Design color system for both light and dark modes
 * 
 * The composable:
 * - Detects system theme using isSystemInDarkTheme()
 * - Applies appropriate color scheme (light or dark)
 * - Updates UI elements to reflect current theme
 * - Uses proper color tokens for text and backgrounds
 * 
 * Key features:
 * - Responsive to system theme changes
 * - Uses Material Design color system
 * - Maintains proper contrast in both themes
 * - Demonstrates theme-aware component styling
 */
@Composable
fun LightDarkModeExample() {
    // Detect current system theme
    val isDarkTheme = isSystemInDarkTheme()
    
    // Select appropriate color scheme based on theme
    val colors = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    
    // Apply the selected color scheme to Material theme
    MaterialTheme(colorScheme = colors) {
        // Full-screen surface with theme-appropriate background color
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Centered column layout
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Theme-aware text that changes based on current theme
                Text(
                    text = if (isDarkTheme) "Dark Mode" else "Light Mode",
                    color = MaterialTheme.colorScheme.onBackground
                )
                
                // Theme-aware button
                Button(onClick = { /* TODO: Implement theme toggle action */ }) {
                    Text("Try Me!")
                }
            }
        }
    }
}

/**
 * Preview composable for the LightDarkModeExample.
 * This allows viewing the UI in Android Studio's preview pane without running the app.
 * showBackground and showSystemUi parameters enable a realistic preview with system UI elements.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        LightDarkModeExample()
    }
}