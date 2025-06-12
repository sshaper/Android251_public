package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                    AnimatedCardExample()
                }
            }
        }
    }
}

/**
 * AnimatedCardExample demonstrates various animation capabilities in Compose:
 * 1. Color animation using animateColorAsState
 * 2. Size animation using animateDpAsState
 * 3. Elevation animation using animateDpAsState
 * 4. Content visibility animation using AnimatedVisibility
 * 5. Content crossfade animation using Crossfade
 * 
 * The card features:
 * - Expands/collapses on click
 * - Changes color smoothly
 * - Changes elevation
 * - Changes width
 * - Shows/hides content with animation
 * - Crossfades between different text states
 */
@Composable
fun AnimatedCardExample() {
    // State variables for animations
    var expanded by remember { mutableStateOf(false) }
    var showDetails by remember { mutableStateOf(false) }
    
    // Animated values
    val cardColor by animateColorAsState(
        targetValue = if (expanded) Color(0xFFBBDEFB) else Color.White,
        label = "cardColor"
    )
    val cardElevation by animateDpAsState(
        targetValue = if (expanded) 26.dp else 4.dp,
        label = "cardElevation"
    )
    val cardWidth by animateDpAsState(
        targetValue = if (expanded) 300.dp else 200.dp,
        label = "cardWidth"
    )

    // Card with animated properties
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = cardElevation
        ),
        modifier = Modifier
            .width(cardWidth)
            .padding(16.dp)
            .padding(top=50.dp)
            .clickable {
                expanded = !expanded
                showDetails = expanded
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Tap to ${if (expanded) "collapse" else "expand"}",
                style = MaterialTheme.typography.titleLarge
            )
            
            // Animated visibility for additional content
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    // Crossfade animation between states
                    Crossfade(targetState = showDetails) { detailsVisible ->
                        if (detailsVisible) {
                            Text("Here are more details! This text fades in.")
                        } else {
                            Text("Tap to see more details.")
                        }
                    }
                }
            }
        }
    }
}

/**
 * Preview composable for the AnimatedCardExample.
 * This allows viewing the UI in Android Studio's preview pane without running the app.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimatedCardPreview() {
    MaterialTheme {
        AnimatedCardExample()
    }
}