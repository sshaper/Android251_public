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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    Column(
        modifier = modifier
            .fillMaxWidth()  // Make the column take full width
            .padding(top = 50.dp)  // Add top margin for spacing from system UI
    ) {
        EditableProfile()
    }
}

/**
 * ProfileInfo is a stateless composable that displays user information.
 * It follows the principle of separation of concerns by only handling display logic.
 * 
 * @param name The user's name to display
 * @param age The user's age to display
 */
@Composable
fun ProfileInfo(name: String, age: Int) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Name: $name",
            style = TextStyle(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Age: $age",
            style = TextStyle(fontSize = 20.sp)
        )
    }
}

/**
 * EditableProfile is a stateful composable that implements a profile editing system.
 * It demonstrates several key Compose concepts:
 * 1. State Management: Uses multiple state variables to track editing mode and user data
 * 2. Unidirectional Data Flow: State is managed at the top and passed down to child composables
 * 3. User Interaction: Handles editing mode toggle and form input
 * 4. Form Validation: Includes basic validation for age input
 * 
 * State Variables:
 * - isEditing: Controls whether the profile is in edit mode
 * - name: Stores the current name value
 * - age: Stores the current age value
 * - tempName/tempAge: Temporary storage for form input before saving
 */
@Composable
fun EditableProfile() {
    // State management for editing mode
    var isEditing by remember { mutableStateOf(false) }

    // State management for profile data
    var name by remember { mutableStateOf("John") }
    var age by remember { mutableStateOf(20) }

    // State management for form input
    var tempName by remember { mutableStateOf(name) }
    var tempAge by remember { mutableStateOf(age.toString()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display current profile information
        ProfileInfo(name = name, age = age)

        Spacer(modifier = Modifier.height(16.dp))

        // Edit/Save button with state-dependent behavior
        Button(onClick = {
            if (isEditing) {
                // Save changes and validate input
                name = tempName
                age = tempAge.toIntOrNull() ?: age
                tempName = ""
                tempAge = ""
            } else {
                // Initialize edit mode with current values
                tempName = name
                tempAge = age.toString()
            }
            isEditing = !isEditing
        }) {
            Text(if (isEditing) "Save" else "Edit")
        }

        // Conditional rendering of edit form
        if (isEditing) {
            Spacer(modifier = Modifier.height(16.dp))

            // Name input field
            OutlinedTextField(
                value = tempName,
                onValueChange = { tempName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Age input field with validation
            OutlinedTextField(
                value = tempAge,
                onValueChange = { tempAge = it },
                label = { Text("Age") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Preview composable for testing the UI in Android Studio's preview pane.
 * Shows the entire MainScreen with the profile editing functionality.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}
