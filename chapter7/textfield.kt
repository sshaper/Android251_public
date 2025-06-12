package com.example.book

// Android framework imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Compose UI imports
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity serves as the entry point of the application.
 * This class extends ComponentActivity which is the base class for activities that use Jetpack Compose.
 */
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
 * MainScreen is the root composable that centers the form in the screen.
 * It uses Box with center alignment to position the form in the middle of the screen.
 */
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SimpleForm()
    }
}

/**
 * SimpleForm is a composable that implements a contact form with three input fields:
 * 1. Name field (basic TextField)
 * 2. Email field (OutlinedTextField with validation)
 * 3. Message field (multi-line OutlinedTextField)
 * 
 * The form includes:
 * - Input validation for email format
 * - Placeholder text for better UX
 * - A submit button that clears the form
 * - Proper spacing and layout using Column and modifiers
 */
@Composable
fun SimpleForm() {
    // State management for form fields using remember and mutableStateOf
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)  // Form takes 80% of screen width
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Form title using Material Design typography
        Text(
            text = "Contact Form",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Name input field with placeholder
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter your full name") }
        )

        // Email input field with validation
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter your email address") },
            isError = email.isNotEmpty() && !email.contains("@")  // Basic email validation
        )

        // Multi-line message input field
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Message") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            minLines = 3,
            maxLines = 5
        )

        // Submit button that clears all form fields
        Button(
            onClick = {
                name = ""
                email = ""
                message = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Submit")
        }
    }
}

/**
 * Preview composable for Android Studio's preview pane.
 * Shows how the app looks with system UI elements.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}
