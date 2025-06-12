package com.example.book

// Android core imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity serves as the entry point of the application.
 * It extends ComponentActivity which is the base class for activities that use Jetpack Compose.
 * The activity sets up the MaterialTheme and initializes the main UI components.
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
 * MainScreen is the root composable that provides the main layout structure.
 * It uses a Box with padding to position the registration form with proper spacing from the top.
 * The Box takes up the full screen size and adds 50dp padding from the top.
 */
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        RegistrationForm()
    }
}

/**
 * RegistrationForm is a composable that implements a complete registration form with validation.
 * It includes fields for name, email, password, and password confirmation with real-time validation.
 * 
 * State Management:
 * - name, email, password, confirmPassword: Store the current input values
 * - isNameValid, isEmailValid, isPasswordValid, isConfirmPasswordValid: Track validation state
 * 
 * Validation Rules:
 * - Name: Must be at least 2 characters, letters and spaces only
 * - Email: Must match standard email format
 * - Password: Must be at least 8 characters with uppercase, lowercase, and numbers
 * - Confirm Password: Must match the password field
 */
@Composable
fun RegistrationForm() {
    // State variables for form fields
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // State variables for validation
    var isNameValid by remember { mutableStateOf(true) }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isConfirmPasswordValid by remember { mutableStateOf(true) }

    // Regular expressions for validation
    val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
    val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$".toRegex()
    val nameRegex = "^[A-Za-z\\s]{2,}$".toRegex()

    // Form layout with proper spacing and padding
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Name input field with validation
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                isNameValid = it.isEmpty() || it.matches(nameRegex)
            },
            label = { Text("Name") },
            isError = !isNameValid && name.isNotEmpty(),
            supportingText = {
                if (!isNameValid && name.isNotEmpty()) {
                    Text("Name must be at least 2 characters and contain only letters and spaces")
                }
            }
        )

        // Email input field with validation
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isEmailValid = it.isEmpty() || it.matches(emailRegex)
            },
            label = { Text("Email") },
            isError = !isEmailValid && email.isNotEmpty(),
            supportingText = {
                if (!isEmailValid && email.isNotEmpty()) {
                    Text("Please enter a valid email address")
                }
            }
        )

        // Password input field with validation and password masking
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isPasswordValid = it.isEmpty() || it.matches(passwordRegex)
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = !isPasswordValid && password.isNotEmpty(),
            supportingText = {
                if (!isPasswordValid && password.isNotEmpty()) {
                    Text("Password must be at least 8 characters with uppercase, lowercase, and numbers")
                }
            }
        )

        // Confirm password field with matching validation
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                isConfirmPasswordValid = it == password
            },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = !isConfirmPasswordValid && confirmPassword.isNotEmpty(),
            supportingText = {
                if (!isConfirmPasswordValid && confirmPassword.isNotEmpty()) {
                    Text("Passwords don't match")
                }
            }
        )

        // Submit button that is enabled only when all fields are valid and filled
        Button(
            onClick = {
                // Handle registration here
                // You can add your registration logic here
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isNameValid && isEmailValid && isPasswordValid &&
                    isConfirmPasswordValid && name.isNotEmpty() &&
                    email.isNotEmpty() && password.isNotEmpty() &&
                    confirmPassword.isNotEmpty()
        ) {
            Text("Register")
        }
    }
}

/**
 * Preview function that allows viewing the RegistrationForm in Android Studio's preview pane.
 * The @Preview annotation enables live preview with background.
 */
@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    MaterialTheme {
        RegistrationForm()
    }
}
