package com.example.book

// Core Android imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

// Compose UI imports
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

// Image loading
import coil.compose.AsyncImage

/**
 * Data class representing a news item with title, description, and image URL.
 * This is used as the data model for our news feed.
 */
data class NewsItem(
    val title: String,
    val description: String,
    val imageUrl: String
)

/**
 * MainActivity is the entry point of the application.
 * It sets up the UI using Jetpack Compose and configures the status bar appearance.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure the status bar to use dark icons (visible on light backgrounds)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        // Set up the Compose UI with Material Design theme
        setContent {
            MaterialTheme {
                Surface {
                    NewsFeedExample()
                }
            }
        }
    }
}

/**
 * NewsFeedExample displays a list of news items in a scrollable feed.
 * Features:
 * - Vertical scrolling with LazyColumn
 * - Sticky header for section title
 * - Clickable news cards
 * - Popup dialog for detailed view
 * - System bar padding to avoid content overlap
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsFeedExample() {
    // Sample data for the news feed
    val newsItems = listOf(
        NewsItem(
            title = "News Article One",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam id massa id ante pretium molestie. Donec a libero pellentesque, hendrerit ante faucibus, feugiat mi. Maecenas fringilla urna quis elit egestas, in tincidunt velit feugiat. ",
            imageUrl = "https://picsum.photos/800/400"
        ),
        NewsItem(
            title = "News Article Two",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam id massa id ante pretium molestie. Donec a libero pellentesque, hendrerit ante faucibus, feugiat mi. Maecenas fringilla urna quis elit egestas, in tincidunt velit feugiat. ",
            imageUrl = "https://picsum.photos/800/401"
        ),
        NewsItem(
            title = "News Article Three",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam id massa id ante pretium molestie. Donec a libero pellentesque, hendrerit ante faucibus, feugiat mi. Maecenas fringilla urna quis elit egestas, in tincidunt velit feugiat. ",
            imageUrl = "https://picsum.photos/800/406"
        ),
        NewsItem(
            title = "News Article Four",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam id massa id ante pretium molestie. Donec a libero pellentesque, hendrerit ante faucibus, feugiat mi. Maecenas fringilla urna quis elit egestas, in tincidunt velit feugiat. ",
            imageUrl = "https://picsum.photos/800/403"
        )
    )

    // State to track which news item is being shown in the dialog
    var selectedNewsItem by remember { mutableStateOf<NewsItem?>(null) }

    // Main container for the news feed
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Vertical scrolling list of news items
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()  // Add padding to avoid system bars
        ) {
            // Sticky header that stays at the top while scrolling
            stickyHeader {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 4.dp  // Add elevation for visual separation
                ) {
                    Text(
                        "Latest News",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            // Display each news item in the list
            items(newsItems) { news ->
                NewsCard(
                    news = news,
                    onClick = { selectedNewsItem = news }  // Show dialog when card is clicked
                )
            }
        }

        // Show dialog when a news item is selected
        selectedNewsItem?.let { news ->
            NewsDialog(
                news = news,
                onDismiss = { selectedNewsItem = null }  // Hide dialog when dismissed
            )
        }
    }
}

/**
 * NewsCard displays a single news item in a card layout.
 * Features:
 * - Clickable card
 * - Image with rounded corners
 * - Title display
 * 
 * @param news The NewsItem to display
 * @param onClick Callback when the card is clicked
 */
@Composable
fun NewsCard(
    news: NewsItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),  // Make card clickable
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Load and display the news image
            AsyncImage(
                model = news.imageUrl,
                contentDescription = news.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Display the news title
            Text(
                text = news.title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

/**
 * NewsDialog displays a popup dialog with the full news item details.
 * Features:
 * - Close button in top-right corner
 * - Full-size image
 * - Title and description
 * - Dismissible by clicking outside or using the back button
 * 
 * @param news The NewsItem to display in the dialog
 * @param onDismiss Callback when the dialog is dismissed
 */
@Composable
fun NewsDialog(
    news: NewsItem,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Close button in top-right corner
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }

                // Display the news image
                AsyncImage(
                    model = news.imageUrl,
                    contentDescription = news.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Display the news title
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Display the full news description
                Text(
                    text = news.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

/**
 * Preview function to see the UI in Android Studio's preview pane.
 * Shows how the news feed looks with sample data.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsFeedExamplePreview() {
    MaterialTheme {
        NewsFeedExample()
    }
}