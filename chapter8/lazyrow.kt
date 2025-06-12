package com.example.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage

/**
 * MainActivity serves as the entry point of the application.
 * It initializes the Compose UI and sets up the window configuration.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        setContent {
           MaterialTheme {
                     ImageCarousel()
            }
        }
    }
}

/**
 * Data class representing an image in the carousel.
 * @property imageUrl The URL of the image to display
 * @property title The title/caption for the image
 */
data class CarouselImage(
    val imageUrl: String,
    val title: String
)

/**
 * ImageCarousel composable creates a horizontally scrollable list of images.
 * Features:
 * - Horizontal scrolling with LazyRow
 * - Proper spacing between items
 * - Top padding to avoid status bar
 * - Efficient image loading with Coil
 */
@Composable
fun ImageCarousel() {
    // Sample image data with placeholder images from picsum.photos
    val images = listOf(
        CarouselImage(
            "https://picsum.photos/800/400?random=1",
            "Image One"
        ),
        CarouselImage(
            "https://picsum.photos/800/400?random=2",
            "Image Two"
        ),
        CarouselImage(
            "https://picsum.photos/800/400?random=3",
            "Image Three"
        ),
        CarouselImage(
            "https://picsum.photos/800/400?random=4",
            "Image Four"
        ),
        CarouselImage(
            "https://picsum.photos/800/400?random=5",
            "Image Five"
        )
    )

    // Horizontal scrolling container with proper spacing and padding
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)  // Add top padding to avoid status bar
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),  // Space between items
        contentPadding = PaddingValues(horizontal = 16.dp)    // Padding at start and end
    ) {
        items(images) { image ->
            CarouselItem(image)
        }
    }
}

/**
 * CarouselItem composable displays a single image card in the carousel.
 * Features:
 * - Material Design card with elevation
 * - Rounded corners
 * - Image with proper scaling
 * - Semi-transparent title overlay
 * 
 * @param image The CarouselImage object containing the image data
 */
@Composable
fun CarouselItem(image: CarouselImage) {
    Card(
        modifier = Modifier
            .width(300.dp)    // Fixed width for consistent card size
            .height(200.dp),  // Fixed height for consistent card size
        shape = RoundedCornerShape(12.dp),  // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)  // Card shadow
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Load and display the image with proper scaling
            AsyncImage(
                // AsyncImage is a Compose component that efficiently loads and displays images from URLs
                // It handles:
                // - Asynchronous image loading
                // - Caching
                // - Memory management
                // - Loading states
                // - Error handling
                model = image.imageUrl,
                contentDescription = image.title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop  // Crop image to fill the space
            )
            
            // Semi-transparent overlay for the title
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)  // Semi-transparent background
            ) {
                Text(
                    text = image.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

