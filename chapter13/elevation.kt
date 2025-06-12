package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                    ElevationExample()
                }
            }
        }
    }
}

/**
 * ElevationExample demonstrates different elevation levels in Material Design 3.
 * This composable shows three different components with varying elevation levels:
 * 1. A low-elevation Surface (2dp)
 * 2. A medium-elevation Card (8dp)
 * 3. A high-elevation Surface (12dp)
 * 4. A FloatingActionButton with 6dp elevation
 * 
 * The components are arranged vertically in a Column with consistent 25dp spacing
 * between them. The layout demonstrates how elevation affects the visual hierarchy
 * and depth of different Material Design components.
 */
@Composable
fun ElevationExample() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 50.dp)
    ) {
        // Low elevation surface (2dp) - subtle elevation for basic content
        Surface(
            tonalElevation = 2.dp,
            shadowElevation = 2.dp,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text("Low elevation (Surface)", modifier = Modifier.padding(16.dp))
        }
        
        // Spacer creates consistent 25dp vertical spacing between components
        Spacer(modifier = Modifier.height(25.dp))
        
        // Medium elevation card (8dp) - standard elevation for interactive content
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text("Higher elevation (Card)", modifier = Modifier.padding(16.dp))
        }
        
        // Spacer creates consistent 25dp vertical spacing between components
        Spacer(modifier = Modifier.height(25.dp))
        
        // High elevation surface (12dp) - prominent elevation for important content
        Surface(
            tonalElevation = 12.dp,
            shadowElevation = 12.dp
        ) {
            Text("Very high elevation (Surface)", modifier = Modifier.padding(16.dp))
        }
        
        // Spacer creates consistent 25dp vertical spacing before FAB
        Spacer(modifier = Modifier.height(25.dp))
        
        // Floating Action Button with medium elevation (6dp)
        // Used for primary actions in the app
        FloatingActionButton(
            onClick = { /* TODO: Implement FAB action */ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            elevation = FloatingActionButtonDefaults.elevation(6.dp)
        ) {
            Text("+")
        }
    }
}

/**
 * Preview composable for the ElevationExample.
 * This allows viewing the UI in Android Studio's preview pane without running the app.
 * showBackground and showSystemUi parameters enable a realistic preview with system UI elements.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        ElevationExample()
    }
}