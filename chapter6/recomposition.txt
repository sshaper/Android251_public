package com.example.book

// Android framework imports
import android.os.Bundle
import androidx.core.view.WindowCompat

// Activity imports
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Compose UI imports
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity serves as the entry point of the Android application.
 * It initializes the app's UI using Jetpack Compose and configures the window appearance.
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
 * MainScreen is the root composable that serves as the container for the app's content.
 * It provides the basic layout structure and spacing for the app's UI elements.
 * 
 * @param modifier Optional Modifier to customize the layout behavior
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProfileCard()
    }
}

/**
 * ProfileCard demonstrates state management and UI updates in Compose.
 * Features:
 * 1. Editable profile information (name, age, favorite color)
 * 2. Dynamic color changes for the card background
 * 3. State preservation using rememberSaveable
 * 4. Material Design 3 components
 * 
 * State Management:
 * - Uses rememberSaveable for name to survive configuration changes
 * - Uses remember for other state variables
 * - Demonstrates independent recomposition of UI elements
 */
@Composable
fun ProfileCard() {
    // State variables with remember/rememberSaveable to maintain state across recompositions
    var name by rememberSaveable { mutableStateOf("John") }
    var age by remember { mutableStateOf(20) }
    var favoriteColor by remember { mutableStateOf("Blue") }

    // Predefined lists for cycling through different values
    val names = listOf("John", "Jane", "Alex", "Sam")
    val colors = listOf(
        Pair(Color(0xFF2196F3), "Blue"),      // Material Blue
        Pair(Color(0xFF4CAF50), "Green"),     // Material Green
        Pair(Color(0xFFF44336), "Red"),       // Material Red
        Pair(Color(0xFF9C27B0), "Purple")     // Material Purple
    )

    // Indices for cycling through the predefined lists
    var nameIndex by remember { mutableStateOf(0) }
    var colorIndex by remember { mutableStateOf(0) }

    // Main layout container
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Card title
        Text(
            text = "Profile Card",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Profile information card with dynamic background color
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = colors[colorIndex].first
            )
        ) {
            // Profile information container
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Profile information text fields
                Text(
                    text = "Name: $name",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                Text(
                    text = "Age: $age",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                Text(
                    text = "Favorite Color: ${colors[colorIndex].second}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }

        // Action buttons container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Button to cycle through different names
            Button(
                onClick = {
                    nameIndex = (nameIndex + 1) % names.size
                    name = names[nameIndex]
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Change Name")
            }

            // Button to increment age
            Button(
                onClick = { age++ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Change Age")
            }

            // Button to cycle through different colors
            Button(
                onClick = {
                    colorIndex = (colorIndex + 1) % colors.size
                    favoriteColor = colors[colorIndex].second
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Change Color")
            }
        }
    }
}

/**
 * Preview composable for testing the UI in Android Studio's preview pane.
 * Shows the profile card with its initial state.
 */
@Preview(showBackground = true)
@Composable
fun PreviewProfileCard() {
    MaterialTheme {
        ProfileCard()
    }
}
