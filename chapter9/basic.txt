package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * MainActivity is the entry point of the application.
 * It sets up the basic window configuration and initializes the Compose UI.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure the window to use light status bar icons
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        
        // Set up the Compose UI with navigation
        setContent {
            MaterialTheme {
                Surface {
                    NavigationWithArgs()
                }
            }
        }
    }
}

/**
 * NavigationWithArgs sets up the navigation structure of the app with argument passing.
 * It demonstrates how to:
 * 1. Pass arguments between screens using navigation routes
 * 2. Define typed arguments in the navigation graph
 * 3. Extract and use arguments in the destination screens
 * 
 * The navigation uses a pattern of "profile/{userId}" where {userId} is a dynamic parameter
 * that gets passed to the profile screen.
 */
@Composable
fun NavigationWithArgs() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Home screen route - starting point of the navigation
        composable("home") {
            HomeScreen(
                onNavigateToProfile = { userId -> 
                    // Navigate to profile screen with a userId parameter
                    // The route will be constructed as "profile/user123"
                    navController.navigate("profile/$userId") 
                }
            )
        }
        
        // Profile screen route with argument
        // The {userId} in the route is a placeholder for the actual user ID
        composable(
            route = "profile/{userId}",
            // Define the argument type as String
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Extract the userId argument from the navigation back stack entry
            val userId = backStackEntry.arguments?.getString("userId")
            ProfileScreen(
                userId = userId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

/**
 * HomeScreen displays the main screen of the application.
 * It contains a button that navigates to a specific user's profile.
 * 
 * @param onNavigateToProfile Callback function that takes a userId parameter
 *                          and handles navigation to the profile screen
 */
@Composable
fun HomeScreen(onNavigateToProfile: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Button that triggers navigation with a hardcoded user ID
        // In a real app, this would typically come from a user selection or authentication
        Button(onClick = { onNavigateToProfile("user123") }) {
            Text("View Profile")
        }
    }
}

/**
 * ProfileScreen displays the profile information for a specific user.
 * It shows the user ID passed through navigation and provides a way to go back.
 * 
 * @param userId The ID of the user whose profile is being displayed
 * @param onNavigateBack Callback function to handle navigation back to the home screen
 */
@Composable
fun ProfileScreen(
    userId: String?,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Display the user ID passed through navigation
        Text("Profile for user: $userId")
        Button(onClick = onNavigateBack) {
            Text("Go Back")
        }
    }
}

/**
 * Preview function to see the UI in Android Studio's preview pane.
 * This allows developers to see how the navigation and screens look without running the app.
 * Note: The preview might not show the actual navigation behavior as it's static.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        NavigationWithArgs()
    }
}