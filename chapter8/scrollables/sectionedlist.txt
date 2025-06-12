package com.example.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    SectionedListExample()
                }
            }
        }
    }
}

@Composable
fun SectionedListExample(modifier: Modifier = Modifier
    .padding(top = 50.dp)) {
    // Sample data for our sections
    val favoriteItems = listOf(
        "Favorite Item 1",
        "Favorite Item 2",
        "Favorite Item 3"
    )
    
    val recentItems = listOf(
        "Recent Item 1",
        "Recent Item 2",
        "Recent Item 3"
    )

    LazyColumn(modifier = modifier) {
        item {
            Text(
                "Favorites",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(favoriteItems) { item ->
            ListItem(
                headlineContent = { Text(item) }
            )
        }
        
        item {
            Text(
                "Recent",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(recentItems) { item ->
            ListItem(
                headlineContent = { Text(item) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SectionedListExamplePreview() {
    MaterialTheme {
        SectionedListExample()
    }
} 