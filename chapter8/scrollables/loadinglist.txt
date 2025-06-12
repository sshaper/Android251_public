package com.example.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
        setContent {
            MaterialTheme {
                Surface {
                    LoadingListExample()
                }
            }
        }
    }
}

@Composable
fun LoadingListExample(modifier: Modifier = Modifier.padding(top = 50.dp)) {
    // State for loading
    var isLoading by remember { mutableStateOf(true) }

    // Launch a coroutine to change isLoading after 2 seconds
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds
        isLoading = false
    }

    if (isLoading) {
        LazyColumn(modifier = modifier) {
            items(3) {
                ListItem(
                    headlineContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
            }
        }
    } else {
        // Show your actual list here
        LazyColumn(modifier = modifier) {
            items(listOf("Item 1", "Item 2", "Item 3")) { item ->
                ListItem(
                    headlineContent = { Text(item) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingListExamplePreview() {
    MaterialTheme {
        LoadingListExample()
    }
}