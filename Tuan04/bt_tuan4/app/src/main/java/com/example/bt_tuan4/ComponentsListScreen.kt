package com.example.bt_tuan4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

sealed class ComponentListItem {
    data class Header(val title: String) : ComponentListItem()
    data class ComponentItem(val title: String, val subtitle: String, val route: String) : ComponentListItem()
}

val componentsList = listOf(
    ComponentListItem.Header("Display"),
    ComponentListItem.ComponentItem("Text", "Displays text", "text"),
    ComponentListItem.ComponentItem("Image", "Displays an image", "image"),
    ComponentListItem.Header("Input"),
    ComponentListItem.ComponentItem("TextField", "Input field for text", "input"),
    ComponentListItem.Header("Layout"),
    ComponentListItem.ComponentItem("Column", "Arranges elements vertically", "column"),
    ComponentListItem.ComponentItem("Row", "Arranges elements horizontally", "row"),
    ComponentListItem.ComponentItem("Box", "Stacks elements", "box"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("UI Components List", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF0000CD) // Medium Blue
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(componentsList) { item ->
                when (item) {
                    is ComponentListItem.Header -> {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    is ComponentListItem.ComponentItem -> {
                        ComponentCard(component = item, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun ComponentCard(component: ComponentListItem.ComponentItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(component.route) },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBBDEFB)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = component.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = component.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
        }
    }
}