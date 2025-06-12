@Composable
fun ScrollableListExample(modifier: Modifier = Modifier
    .padding(top = 50.dp)) {
    LazyColumn(modifier = modifier) {
        items(100) { index ->
            ListItem(
                headlineContent = { Text("Item $index") }
            )
        }
    }
} 