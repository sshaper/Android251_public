package com.example.multiplefiles.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.multiplefiles.screens.home.HomeScreen
import com.example.multiplefiles.screens.profile.ProfileScreen
import com.example.multiplefiles.screens.settings.SettingsScreen

object NavRoutes {
    const val HOME = "home"
    const val PROFILE = "profile/{userName}"
    const val SETTINGS = "settings"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.HOME) {
        composable(NavRoutes.HOME) {
            HomeScreen(
                onProfileClick = { userName ->
                    navController.navigate("profile/$userName")
                },
                onSettingsClick = {
                    navController.navigate(NavRoutes.SETTINGS)
                }
            )
        }
        composable(
            route = NavRoutes.PROFILE,
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Unknown"
            ProfileScreen(
                userName = userName,
                onHomeClick = { navController.navigate(NavRoutes.HOME) },
                onSettingsClick = { navController.navigate(NavRoutes.SETTINGS) }
            )
        }
        composable(NavRoutes.SETTINGS) {
            SettingsScreen(
                onHomeClick = { navController.navigate(NavRoutes.HOME) },
                onProfileClick = { userName -> navController.navigate("profile/$userName") }
            )
        }
    }
} 