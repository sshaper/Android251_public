package com.example.book

import android.os.Bundle
import android.widget.Toast
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

/**
 * Data class representing a task in the list.
 */
data class Task(
    val id: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)

/**
 * Enum for selection mode: single or multiple.
 */
enum class SelectionMode { SINGLE, MULTIPLE }

/**
 * MainActivity is the entry point of the app.
 * It sets up the Compose UI and configures the status bar appearance.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set status bar icons to dark for visibility on light backgrounds
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
        setContent {
            MaterialTheme {
                TaskListApp()
            }
        }
    }
}

/**
 * Top-level composable that provides sample data and launches the task list UI.
 */
@Composable
fun TaskListApp() {
    // Sample data for our task list
    val tasks = listOf(
        Task("1", "Study for Math Exam", "Review chapters 1-5"),
        Task("2", "Complete Programming Assignment", "Finish the Android app"),
        Task("3", "Read History Chapter", "Read pages 50-75"),
        Task("4", "Write Essay", "Draft the introduction"),
        Task("5", "Group Project Meeting", "Prepare presentation slides")
    )
    TaskList(tasks = tasks)
}

/**
 * TaskList displays a list of tasks with single or multiple selection modes.
 * - Two mode buttons at the top allow switching between single and multiple selection.
 * - In single selection mode, clicking a row or checkbox selects only that task.
 * - In multiple selection mode, clicking a row or checkbox toggles the selection of that task.
 * - Long press in single selection mode switches to multiple selection and selects the task.
 * - A header with actions appears in multiple selection mode when tasks are selected.
 */
@Composable
fun TaskList(tasks: List<Task>) {
    var selectionMode by remember { mutableStateOf(SelectionMode.SINGLE) }
    var selectedTask by remember { mutableStateOf<String?>(null) }
    var selectedTasks by remember { mutableStateOf(setOf<String>()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 50.dp)
    ) {
        // Mode selector buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    selectionMode = SelectionMode.SINGLE
                    selectedTasks = emptySet()
                    selectedTask = null
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectionMode == SelectionMode.SINGLE) Color(0xFF4CAF50) else Color(0xFFF44336), // Green if selected, red if not
                    contentColor = Color.White
                )
            ) {
                Text("Single Selection")
            }
            Button(
                onClick = {
                    selectionMode = SelectionMode.MULTIPLE
                    selectedTask = null
                    selectedTasks = emptySet()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectionMode == SelectionMode.MULTIPLE) Color(0xFF4CAF50) else Color(0xFFF44336), // Green if selected, red if not
                    contentColor = Color.White
                )
            ) {
                Text("Multiple Selection")
            }
        }

        // Selection mode header for multiple selection
        if (selectionMode == SelectionMode.MULTIPLE && selectedTasks.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "${selectedTasks.size} tasks selected",
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    IconButton(onClick = {
                        Toast.makeText(context, "Completing ${selectedTasks.size} tasks", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Check, "Complete")
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Deleting ${selectedTasks.size} tasks", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Delete, "Delete")
                    }
                }
            }
        }

        // Task list using LazyColumn
        LazyColumn {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    isSelected = if (selectionMode == SelectionMode.SINGLE) selectedTask == task.id else selectedTasks.contains(task.id),
                    selectionMode = selectionMode,
                    onTaskClick = {
                        if (selectionMode == SelectionMode.SINGLE) {
                            selectedTask = task.id
                            selectedTasks = emptySet()
                            Toast.makeText(context, "Selected: ${task.title}", Toast.LENGTH_SHORT).show()
                        } else {
                            selectedTask = null
                            selectedTasks = if (selectedTasks.contains(task.id)) {
                                selectedTasks - task.id
                            } else {
                                selectedTasks + task.id
                            }
                        }
                    },
                    onTaskLongPress = {
                        if (selectionMode == SelectionMode.SINGLE) {
                            selectionMode = SelectionMode.MULTIPLE
                            selectedTasks = setOf(task.id)
                            selectedTask = null
                        }
                    }
                )
            }
        }
    }
}

/**
 * TaskItem displays a single task row.
 * - Shows a checkbox for selection.
 * - Uses combinedClickable to support both click and long-press.
 * - In single selection mode, clicking selects only this task.
 * - In multiple selection mode, clicking toggles selection.
 * - Long press in single selection mode switches to multiple selection and selects the task.
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    task: Task,
    isSelected: Boolean,
    selectionMode: SelectionMode,
    onTaskClick: () -> Unit,
    onTaskLongPress: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
            )
            .combinedClickable(
                onClick = onTaskClick,
                onLongClick = onTaskLongPress
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onTaskClick() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/**
 * Preview function for Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskListPreview() {
    MaterialTheme {
        TaskListApp()
    }
}