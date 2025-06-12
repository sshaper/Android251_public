package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
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
                    ThemeComparisonExample()
                }
            }
        }
    }
}

/**
 * ThemeComparisonExample demonstrates how different sections of an app
 * can use distinct themes to indicate their purpose and access level.
 * 
 * The example shows:
 * 1. Admin section with a red theme (indicating restricted access)
 * 2. User section with a blue theme (indicating standard access)
 * 
 * Each section demonstrates:
 * - Custom color schemes
 * - Themed components
 * - Visual hierarchy through color
 */
@Composable
fun ThemeComparisonExample() {
    // Row to display both sections side by side
    Row(modifier = Modifier.fillMaxSize().padding(top = 50.dp)) {
        // Admin section with red theme
        AdminSection(modifier = Modifier.weight(1f))
        
        // User section with blue theme
        UserSection(modifier = Modifier.weight(1f))
    }
}

/**
 * Admin section with a red theme to indicate restricted access.
 * Uses a custom color scheme with red as primary color.
 */
@Composable
fun AdminSection(modifier: Modifier = Modifier) {
    // Admin color scheme with red as primary color
    val adminColors = lightColorScheme(
        primary = Color(0xFFB00020),    // Deep red
        secondary = Color(0xFF3700B3),  // Deep purple
        background = Color(0xFFFFEBEE)  // Light red background
    )
    
    MaterialTheme(colorScheme = adminColors) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Admin Area",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Button(
                    onClick = { /* TODO: Implement admin action */ },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Admin Action")
                }
            }
        }
    }
}

/**
 * User section with a blue theme to indicate standard access.
 * Uses a custom color scheme with blue as primary color.
 */
@Composable
fun UserSection(modifier: Modifier = Modifier) {
    // User color scheme with blue as primary color
    val userColors = lightColorScheme(
        primary = Color(0xFF1976D2),    // Blue
        secondary = Color(0xFF03DAC6),  // Teal
        background = Color(0xFFE3F2FD)  // Light blue background
    )
    
    MaterialTheme(colorScheme = userColors) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "User Area",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Button(
                    onClick = { /* TODO: Implement user action */ },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("User Action")
                }
            }
        }
    }
}

/**
 * Preview composable for the theme comparison example.
 * This allows viewing the UI in Android Studio's preview pane without running the app.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThemeComparisonPreview() {
    MaterialTheme {
        ThemeComparisonExample()
    }
}