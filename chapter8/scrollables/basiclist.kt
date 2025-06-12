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
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
        setContent {
            MaterialTheme {
                Surface {
                    BasicListExample()
                }
            }
        }
    }
}

@Composable
fun BasicListExample(modifier: Modifier = Modifier
    .padding(top = 50.dp)) {
    // Sample data for our list
    val items = listOf(
        "First Item",
        "Second Item",
        "Third Item",
        "Fourth Item",
        "Fifth Item",
        "Sixth Item",
        "Seventh Item",
        "Eighth Item",
        "Ninth Item",
        "Tenth Item",
        "Eleventh Item",
        "Twelfth Item",
        "Thirteenth Item",
        "Fourteenth Item",
        "Fifteenth Item",
        "Sixteenth Item",
        "Seventeenth Item",
    )

    LazyColumn(modifier = modifier) {
        items(items) { item ->
            ListItem(
                headlineContent = { Text(item) },
                supportingContent = { Text("Tap to see more") }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BasicListExamplePreview() {
    MaterialTheme {
        BasicListExample()
    }
}