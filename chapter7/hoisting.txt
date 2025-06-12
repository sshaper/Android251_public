package com.example.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

/**
 * MainActivity serves as the entry point of the application.
 * It extends ComponentActivity which is the base class for activities that use Jetpack Compose.
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
 * MainScreen is the root composable that serves as a container for the main UI content.
 * It follows the single responsibility principle by delegating the form display to FormScreen.
 */
@Composable
fun MainScreen() {
    FormScreen()
}

/**
 * FormScreen is a composable that implements a form with name and email fields.
 * It demonstrates state hoisting by managing form state at this level and passing it down to child composables.
 * 
 * State Management:
 * - name: Tracks the text entered in the name field
 * - email: Tracks the text entered in the email field
 * - isFormValid: Boolean state that tracks if both fields are filled out
 */
@Composable
fun FormScreen() {
    // State variables are hoisted to the parent composable for better state management
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(false) }

    // Create a vertical layout for form elements with proper spacing and padding
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // NameInput component with state passed from parent
        NameInput(
            value = name,
            onValueChange = {
                name = it
                isFormValid = name.isNotEmpty() && email.isNotEmpty()
            }
        )

        // EmailInput component with state passed from parent
        EmailInput(
            value = email,
            onValueChange = {
                email = it
                isFormValid = name.isNotEmpty() && email.isNotEmpty()
            }
        )

        // Submit button that is conditionally enabled based on form validity
        Button(
            onClick = { /* Handle submit */ },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid
        ) {
            Text("Submit")
        }
    }
}

/**
 * NameInput is a reusable composable that displays a text field for name input.
 * It follows the unidirectional data flow pattern by receiving its state from the parent.
 * 
 * @param value The current text value of the name field
 * @param onValueChange Callback function to handle text changes
 */
@Composable
fun NameInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * EmailInput is a reusable composable that displays a text field for email input.
 * It follows the unidirectional data flow pattern by receiving its state from the parent.
 * 
 * @param value The current text value of the email field
 * @param onValueChange Callback function to handle text changes
 */
@Composable
fun EmailInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * Preview function that allows viewing the UI in Android Studio's preview pane.
 * The @Preview annotation enables live preview with system UI and background.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFlow() {
    MainScreen()
}
