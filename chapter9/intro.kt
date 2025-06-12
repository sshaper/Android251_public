package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Navigation imports
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * MainActivity is the entry point of the application.
 * It sets up the basic window configuration and initializes the Compose UI.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure the window to use light status bar icons
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        
        // Set up the Compose UI
        setContent {
            MaterialTheme {
                Surface {
                    SimpleNavigationExample()
                }
            }
        }
    }
}

/**
 * SimpleNavigationExample sets up the navigation structure of the app.
 * It uses Jetpack Compose Navigation to manage screen transitions between Home and Profile screens.
 * 
 * The navigation is implemented using a NavHost, which is the container for all navigation-related
 * components. It manages the navigation graph and handles the navigation between different screens.
 */
@Composable
fun SimpleNavigationExample() {
    // Create a NavController to handle navigation between screens
    val navController = rememberNavController()

    // Define the navigation graph
    NavHost(
        navController = navController,
        startDestination = "home" // The first screen to show when the app starts
    ) {
        // Define the home screen route
        composable("home") {
            HomeScreen(
                onNavigateToProfile = {
                    // Navigate to profile screen with launchSingleTop=true
                    // This prevents multiple instances of the same screen in the back stack
                    // and helps avoid animation state issues that can occur with rapid navigation
                    navController.navigate("profile") {
                        launchSingleTop = true
                    }
                }
            )
        }
        // Define the profile screen route
        composable("profile") {
            ProfileScreen(
                onNavigateBack = {
                    // Pop the current screen off the back stack to return to the previous screen
                    navController.popBackStack()
                }
            )
        }
    }
}

/**
 * HomeScreen displays the main screen of the application.
 * It contains a button to navigate to the profile screen and a text message.
 * 
 * @param onNavigateToProfile Callback function to handle navigation to the profile screen
 */
@Composable
fun HomeScreen(onNavigateToProfile: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onNavigateToProfile) {
                Text("Go to Profile")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "This is the home page",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

/**
 * ProfileScreen displays the profile screen of the application.
 * It contains a button to navigate back to the home screen and a text message.
 * 
 * @param onNavigateBack Callback function to handle navigation back to the home screen
 */
@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onNavigateBack) {
                Text("Back to Home")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "This is the profile page",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

/**
 * Preview function to see the UI in Android Studio's preview pane.
 * This allows developers to see how the navigation and screens look without running the app.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        SimpleNavigationExample()
    }
}