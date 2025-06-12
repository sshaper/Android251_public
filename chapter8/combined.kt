package com.example.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage

/**
 * MainActivity serves as the entry point of the application.
 * It initializes the Compose UI and sets up the window configuration.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure status bar to use light theme (dark icons)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        setContent {
            MaterialTheme {
                CategoryList()
            }
        }
    }
}

/**
 * Data classes for organizing content in a hierarchical structure.
 * Category represents a section of content with multiple items.
 * CategoryItem represents individual items within a category.
 */
data class Category(
    val id: Int,
    val name: String,
    val items: List<CategoryItem>
)

data class CategoryItem(
    val id: Int,
    val title: String,
    val imageUrl: String
)

/**
 * CategoryList composable creates a vertically scrolling list of categories.
 * Each category contains a horizontally scrolling list of items.
 * Features:
 * - Vertical scrolling with LazyColumn
 * - Multiple categories with horizontal scrolling items
 * - Proper spacing and padding
 * - Efficient image loading with Coil
 */
@Composable
fun CategoryList() {
    // Create sample data with 7 categories, each containing 10 items
    val categories = List(7) { categoryIndex ->
        Category(
            id = categoryIndex,
            name = "Category ${categoryIndex + 1}",
            items = List(10) { itemIndex ->
                CategoryItem(
                    id = itemIndex,
                    title = "Title ${itemIndex + 1}",
                    // Using placeholder images from picsum.photos
                    imageUrl = "https://picsum.photos/200/300?random=${categoryIndex * 10 + itemIndex}"
                )
            }
        )
    }

    // Vertical scrolling container for categories
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),  // Add top padding to avoid status bar
        verticalArrangement = Arrangement.spacedBy(16.dp),  // Space between categories
        contentPadding = PaddingValues(16.dp)  // Padding around the content
    ) {
        items(categories) { category ->
            CategorySection(category)
        }
    }
}

/**
 * CategorySection composable displays a single category with its items.
 * Features:
 * - Category title
 * - Horizontally scrolling list of items
 * - Proper spacing between items
 * 
 * @param category The Category object containing the category data
 */
@Composable
fun CategorySection(category: Category) {
    Column {
        // Category title with proper spacing
        Text(
            text = category.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Horizontal scrolling container for category items
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)  // Space between items
        ) {
            items(category.items) { item ->
                CategoryItemCard(item)
            }
        }
    }
}

/**
 * CategoryItemCard composable displays a single item in a category.
 * Features:
 * - Material Design card with elevation
 * - Image with proper scaling
 * - Title with ellipsis for long text
 * 
 * @param item The CategoryItem object containing the item data
 */
@Composable
fun CategoryItemCard(item: CategoryItem) {
    Card(
        modifier = Modifier
            .width(160.dp)    // Fixed width for consistent card size
            .height(200.dp),  // Fixed height for consistent card size
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)  // Card shadow
    ) {
        Column {
            // Load and display the image with proper scaling
            AsyncImage(
                // AsyncImage is a Compose component that efficiently loads and displays images from URLs
                // It handles:
                // - Asynchronous image loading
                // - Caching
                // - Memory management
                // - Loading states
                // - Error handling
                model = item.imageUrl,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop  // Crop image to fill the space
            )

            // Title with ellipsis for long text
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,  // Limit to 2 lines
                overflow = TextOverflow.Ellipsis,  // Show ellipsis for overflow
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

/**
 * Preview function for the CategoryList composable.
 * Shows how the UI looks in Android Studio's preview pane.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryListPreview() {
    MaterialTheme {
        CategoryList()
    }
}
