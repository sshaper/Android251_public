package com.example.book

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.window.Dialog

/**
 * MainActivity is the entry point of the application.
 * It sets up the Compose UI and configures the status bar appearance.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure the status bar to use dark icons (visible on light backgrounds)
        androidx.core.view.WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
        setContent {
            PhotoGalleryExample()
        }
    }
}

/**
 * Data class representing a photo item in the gallery.
 */
data class Photo(
    val id: String,
    val url: String,
    val description: String
)

/**
 * Enum representing the selection mode for the gallery.
 */
enum class SelectionMode(val label: String) {
    SINGLE("Single Selection"),
    MULTIPLE("Multiple Selection"),
    CHECKBOX("Checkbox Selection")
}

/**
 * Top-level composable that provides sample data and launches the gallery UI.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGalleryExample() {
    // Sample data for our photo gallery
    val photos = listOf(
        Photo("1", "https://picsum.photos/400/400?random=1", "Working at table"),
        Photo("2", "https://picsum.photos/400/400?random=2", "Sunset"),
        Photo("3", "https://picsum.photos/400/400?random=3", "Mountain path"),
        Photo("4", "https://picsum.photos/400/400?random=4", "Looking outside"),
        Photo("5", "https://picsum.photos/400/400?random=5", "Desert"),
        Photo("6", "https://picsum.photos/400/400?random=6", "Mountain Side")
    )
    PhotoGallery(photos = photos)
}

/**
 * PhotoGallery displays a grid of photos with selectable modes (single, multiple, checkbox).
 * - The user can switch modes using the selector at the top.
 * - Single click selects a photo (single or multiple selection).
 * - Long-press in single mode shows a large preview of the image.
 * - In multiple/checkbox mode, a header with actions appears when items are selected.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGallery(photos: List<Photo>) {
    var selectionMode by remember { mutableStateOf(SelectionMode.SINGLE) }
    var selectedPhotos by remember { mutableStateOf(setOf<String>()) }
    var previewPhoto by remember { mutableStateOf<Photo?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.systemBarsPadding()
    ) {
        // Mode selector buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SelectionMode.values().forEach { mode ->
                val isActive = selectionMode == mode
                Button(
                    onClick = {
                        selectionMode = mode
                        selectedPhotos = emptySet()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        contentColor = if (isActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier,
                    enabled = true
                ) {
                    Text(mode.name)
                }
            }
        }
        // Show current selection mode label
        Text(
            text = "Current mode: ${selectionMode.label}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Selection mode header for MULTIPLE and CHECKBOX
        if ((selectionMode == SelectionMode.MULTIPLE || selectionMode == SelectionMode.CHECKBOX) && selectedPhotos.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "${selectedPhotos.size} photos selected",
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    IconButton(onClick = {
                        Toast.makeText(context, "Sharing ${selectedPhotos.size} photos", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Share, "Share")
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Deleting ${selectedPhotos.size} photos", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Delete, "Delete")
                    }
                    IconButton(onClick = {
                        selectedPhotos = emptySet()
                    }) {
                        Icon(Icons.Default.Close, "Close")
                    }
                }
            }
        }

        // Photo grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(photos) { photo ->
                when (selectionMode) {
                    SelectionMode.SINGLE -> PhotoItemSingle(
                        photo = photo,
                        isSelected = selectedPhotos.contains(photo.id),
                        onPhotoClick = {
                            selectedPhotos = setOf(photo.id)
                            Toast.makeText(context, "Selected: ${photo.description}", Toast.LENGTH_SHORT).show()
                        },
                        onPhotoLongPress = {
                            previewPhoto = photo
                        }
                    )
                    SelectionMode.MULTIPLE -> PhotoItemMultiple(
                        photo = photo,
                        isSelected = selectedPhotos.contains(photo.id),
                        onPhotoClick = {
                            selectedPhotos = if (selectedPhotos.contains(photo.id)) {
                                selectedPhotos - photo.id
                            } else {
                                selectedPhotos + photo.id
                            }
                        }
                    )
                    SelectionMode.CHECKBOX -> PhotoItemCheckbox(
                        photo = photo,
                        isSelected = selectedPhotos.contains(photo.id),
                        onCheckedChange = { checked ->
                            selectedPhotos = if (checked) {
                                selectedPhotos + photo.id
                            } else {
                                selectedPhotos - photo.id
                            }
                        }
                    )
                }
            }
        }

        // Large preview dialog if a photo is long-pressed
        previewPhoto?.let { photo ->
            Dialog(onDismissRequest = { previewPhoto = null }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.surface, RectangleShape)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = photo.url,
                        contentDescription = photo.description,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

/**
 * PhotoItemSingle displays a single photo for single selection mode.
 * - Shows a red border if selected.
 * - Handles both single click (select) and long-press (preview).
 *
 * @OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class) is used because:
 *   - ExperimentalComposeUiApi::class is required for pointer input APIs (if you use pointerInteropFilter or similar low-level pointer handling).
 *   - ExperimentalFoundationApi::class is required for combinedClickable, which is still marked experimental in Compose.
 *   - In this composable, combinedClickable is used to allow both single click and long-press gestures on the same item, which is not possible with just clickable or pointerInteropFilter alone.
 *   - Using both OptIn annotations ensures the code compiles and you acknowledge the experimental status of these APIs.
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun PhotoItemSingle(
    photo: Photo,
    isSelected: Boolean,
    onPhotoClick: () -> Unit,
    onPhotoLongPress: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .combinedClickable(
                onClick = {
                    onPhotoClick()
                },
                onLongClick = {
                    onPhotoLongPress()
                }
            )
            .border(
                width = if (isSelected) 4.dp else 0.dp,
                color = if (isSelected) Color.Red else Color.Transparent, // Use Color.Red for visibility
                shape = RectangleShape
            )
    ) {
        AsyncImage(
            model = photo.url,
            contentDescription = photo.description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

/**
 * PhotoItemMultiple displays a photo for multiple selection mode.
 * - Shows a border and checkmark overlay if selected.
 */
@Composable
fun PhotoItemMultiple(
    photo: Photo,
    isSelected: Boolean,
    onPhotoClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .clickable(onClick = onPhotoClick)
            .border(
                width = if (isSelected) 4.dp else 0.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RectangleShape
            )
    ) {
        AsyncImage(
            model = photo.url,
            contentDescription = photo.description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "Selected",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    tint = Color.White
                )
            }
        }
    }
}

/**
 * PhotoItemCheckbox displays a photo with a checkbox for selection.
 * - Shows a border if selected.
 */
@Composable
fun PhotoItemCheckbox(
    photo: Photo,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .border(
                width = if (isSelected) 4.dp else 0.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RectangleShape
            )
    ) {
        AsyncImage(
            model = photo.url,
            contentDescription = photo.description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Checkbox(
            checked = isSelected,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        )
    }
}

/**
 * Preview function for Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PhotoGalleryPreview() {
    MaterialTheme {
        PhotoGalleryExample()
    }
}
