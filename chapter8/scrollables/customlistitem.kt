package com.example.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    CustomListItemExample()
                }
            }
        }
    }
}

@Composable
fun CustomListItemExample(modifier: Modifier = Modifier
    .padding(top = 50.dp)) {
    // Sample data for our list
    val items = listOf(
        ItemData(
            title = "First Item",
            description = "This is a description for the first item",
            imageUrl = "https://picsum.photos/200"
        ),
        ItemData(
            title = "Second Item",
            description = "This is a description for the second item",
            imageUrl = "https://picsum.photos/201"
        ),
        ItemData(
            title = "Third Item",
            description = "This is a description for the third item",
            imageUrl = "https://picsum.photos/202"
        )
    )

    LazyColumn(modifier = modifier) {
        items(items) { item ->
            CustomListItem(
                title = item.title,
                description = item.description,
                imageUrl = item.imageUrl,
                onItemClick = { /* Handle click */ }
            )
        }
    }
}

@Composable
fun CustomListItem(
    title: String,
    description: String,
    imageUrl: String,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

data class ItemData(
    val title: String,
    val description: String,
    val imageUrl: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomListItemExamplePreview() {
    MaterialTheme {
        CustomListItemExample()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomListItemPreview() {
    MaterialTheme {
        CustomListItem(
            title = "Sample Item",
            description = "This is a sample description",
            imageUrl = "https://picsum.photos/200",
            onItemClick = {}
        )
    }
} 