package com.example.book

// Android core imports for basic functionality
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports for building the interface
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * MainActivity is the entry point of the application.
 * It sets up the UI using Jetpack Compose and handles the status bar appearance.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configure the status bar to use dark icons (visible on light backgrounds)
        // This makes the status bar icons (time, battery, etc.) dark colored
        // so they're visible against the light app background
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        
        // Set up the Compose UI
        setContent {
            // Apply Material Design theme to the entire app
            MaterialTheme {
                     LazyColumnExample()
            }
        }
    }
}

/**
 * Data class representing a contact with name and email.
 * This is used to store and display contact information in the list.
 */
data class Contact(
    val name: String,    // The contact's full name
    val email: String    // The contact's email address
)

/**
 * LazyColumnExample demonstrates the use of LazyColumn for efficient list rendering.
 * LazyColumn only renders items that are visible on screen, making it efficient for long lists.
 */
@Composable
fun LazyColumnExample(modifier: Modifier = Modifier
    .padding(top =50.dp)) {
    // Sample data: List of 25 contacts with names and email addresses
    val contacts = listOf(
        Contact("John Doe", "john.doe@example.com"),
        Contact("Jane Smith", "jane.smith@example.com"),
        Contact("Bob Johnson", "bob.johnson@example.com"),
        Contact("Alice Brown", "alice.brown@example.com"),
        Contact("Charlie Wilson", "charlie.wilson@example.com"),
        Contact("Emma Davis", "emma.davis@example.com"),
        Contact("Michael Taylor", "michael.taylor@example.com"),
        Contact("Sarah Anderson", "sarah.anderson@example.com"),
        Contact("David Martinez", "david.martinez@example.com"),
        Contact("Lisa Thompson", "lisa.thompson@example.com"),
        Contact("Robert Garcia", "robert.garcia@example.com"),
        Contact("Jennifer White", "jennifer.white@example.com"),
        Contact("William Lee", "william.lee@example.com"),
        Contact("Patricia Harris", "patricia.harris@example.com"),
        Contact("James Clark", "james.clark@example.com"),
        Contact("Mary Lewis", "mary.lewis@example.com"),
        Contact("Thomas Robinson", "thomas.robinson@example.com"),
        Contact("Nancy Walker", "nancy.walker@example.com"),
        Contact("Daniel Young", "daniel.young@example.com"),
        Contact("Karen Allen", "karen.allen@example.com"),
        Contact("Matthew King", "matthew.king@example.com"),
        Contact("Betty Wright", "betty.wright@example.com"),
        Contact("Anthony Scott", "anthony.scott@example.com"),
        Contact("Rebecca Green", "rebecca.green@example.com"),
        Contact("Steven Baker", "steven.baker@example.com")
    )

    // Main LazyColumn layout that displays the list of contacts
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()  // Make the column take up full width
            .padding(16.dp), // Add padding around the edges
        verticalArrangement = Arrangement.spacedBy(8.dp) // Add space between items
    ) {
        // Add each contact to the list using the items function
        // This efficiently renders only the contacts that are visible on screen
        items(contacts) { contact ->
            // Display each contact in a card
            ContactCard(contact)
        }
    }
}

/**
 * ContactCard is a composable that displays a single contact's information in a card.
 * It shows the contact's name and email in a visually appealing format.
 */
@Composable
fun ContactCard(contact: Contact) {
    // Create a card with elevation for a raised appearance
    Card(
        modifier = Modifier.fillMaxWidth(), // Make the card take up full width
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Add shadow effect
    ) {
        // Create a column layout for the contact information
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Add padding inside the card
            verticalArrangement = Arrangement.spacedBy(4.dp) // Add space between name and email
        ) {
            // Display the contact's name in a larger, bold style
            Text(
                text = contact.name,
                style = MaterialTheme.typography.titleMedium
            )
            // Display the contact's email in a smaller, secondary style
            Text(
                text = contact.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant // Use a slightly muted color
            )
        }
    }
}

/**
 * Preview function allows us to see the UI in Android Studio's preview pane
 * without running the app on a device or emulator.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LazyColumnExamplePreview() {
    MaterialTheme {
        LazyColumnExample()
    }
}