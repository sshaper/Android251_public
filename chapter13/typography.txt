package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                    StyleExample()
                }
            }
        }
    }
}

/**
 * StyleExample demonstrates Material Design 3 styling capabilities including:
 * 1. Card styling with custom shape and colors
 * 2. Typography system usage
 * 3. Color system integration
 * 4. Button styling with Material shapes
 * 
 * The composable shows:
 * - A card with extra-large shape and secondary container color
 * - Proper use of Material Design typography for different text styles
 * - Color system usage for text and backgrounds
 * - Styled button with Material Design shape
 */
@Composable
fun StyleExample() {
    MaterialTheme {
        // Main card container with custom styling
        Card(
            // Use extra-large shape from Material Design shape system
            shape = MaterialTheme.shapes.extraLarge,
            // Use secondary container color from Material Design color system
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 50.dp)
        ) {
            // Content column with proper padding
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                // Title text using Material Design typography
                Text(
                    text = "Big Title",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                
                // Body text using Material Design typography
                Text(
                    text = "This is body text.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                
                // Styled button using Material Design shape
                Button(
                    onClick = { /* TODO: Implement button action */ },
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Styled Button")
                }
            }
        }
    }
}

/**
 * Preview composable for the StyleExample.
 * This allows viewing the UI in Android Studio's preview pane without running the app.
 * showBackground and showSystemUi parameters enable a realistic preview with system UI elements.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        StyleExample()
    }
}