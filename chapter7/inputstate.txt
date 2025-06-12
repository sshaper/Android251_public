package com.example.book

// Android core imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity serves as the entry point of the Android application.
 * It initializes the app's UI using Jetpack Compose and configures the window appearance.
 * The activity sets up:
 * 1. Light status bar icons for better visibility
 * 2. Material Design 3 theming
 * 3. The main login screen as the root composable
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
 * MainScreen is a simple container that holds our login screen.
 * It serves as the root composable for the application's main content.
 */
@Composable
fun MainScreen() {
    LoginScreen()
}

/**
 * LoginScreen implements a login form with username and password fields.
 * Features include:
 * 1. Username validation (minimum 3 characters)
 * 2. Password validation (minimum 6 characters)
 * 3. Password visibility toggle
 * 4. Real-time input validation with error messages
 * 5. Login button that's only enabled when all inputs are valid
 * 
 * State Management:
 * - username: Stores the text entered in the username field
 * - password: Stores the text entered in the password field
 * - showPassword: Controls password visibility
 * - isUsernameValid: Tracks username validation state
 * - isPasswordValid: Tracks password validation state
 */
@Composable
fun LoginScreen() {
    // Create state variables to store user input and UI state
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var isUsernameValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    // Create a vertical layout for our form elements
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Username input field with real-time validation
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                isUsernameValid = it.length >= 3
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            isError = !isUsernameValid && username.isNotEmpty(),
            supportingText = {
                if (!isUsernameValid && username.isNotEmpty()) {
                    Text("Username must be at least 3 characters")
                }
            }
        )

        // Password input field with show/hide functionality and validation
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isPasswordValid = it.length >= 6
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            isError = !isPasswordValid && password.isNotEmpty(),
            supportingText = {
                if (!isPasswordValid && password.isNotEmpty()) {
                    Text("Password must be at least 6 characters")
                }
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showPassword) "Hide password" else "Show password"
                    )
                }
            }
        )

        // Login button that is only enabled when all inputs are valid
        Button(
            onClick = { /* Handle login */ },
            modifier = Modifier.fillMaxWidth(),
            enabled = isUsernameValid && isPasswordValid && username.isNotEmpty() && password.isNotEmpty()
        ) {
            Text("Login")
        }
    }
}

/**
 * Preview composable for development and testing.
 * Shows how the UI looks in Android Studio's preview pane.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}
