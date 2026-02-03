package com.example.btktmh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.btktmh.data.local.AppDatabase
import com.example.btktmh.data.repository.TaskRepository
import com.example.btktmh.ui.add.AddTaskScreen
import com.example.btktmh.ui.add.AddTaskViewModel
import com.example.btktmh.ui.list.TaskListScreen
import com.example.btktmh.ui.list.TaskListViewModel
import com.example.btktmh.ui.theme.BktmhTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getInstance(this)
        val repository = TaskRepository(db.taskDao())

        setContent {
            BktmhTheme {
                SmartTasksApp(repository)
            }
        }
    }
}

private sealed class Dest(val route: String, val label: String) {
    data object Home : Dest("list", "Home")
    data object Calendar : Dest("calendar", "Calendar")
    data object Add : Dest("add", "Add")
    data object Docs : Dest("docs", "Docs")
    data object Settings : Dest("settings", "Settings")
}

@Composable
private fun SmartTasksApp(repository: TaskRepository) {
    val navController = rememberNavController()

    val listVM: TaskListViewModel = viewModel { TaskListViewModel(repository) }
    val addVM: AddTaskViewModel = viewModel { AddTaskViewModel(repository) }

    val items = listOf(Dest.Home, Dest.Calendar, Dest.Add, Dest.Docs, Dest.Settings)

    Scaffold(
        bottomBar = {
            BottomBar(
                items = items,
                currentRoute = currentRoute(navController),
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { inner ->
        NavHost(
            navController = navController,
            startDestination = Dest.Home.route,
            modifier = Modifier
        ) {
            composable(Dest.Home.route) {
                TaskListScreen(
                    viewModel = listVM,
                    onAddClick = { navController.navigate(Dest.Add.route) }
                )
            }
            composable(Dest.Add.route) {
                AddTaskScreen(
                    viewModel = addVM,
                    onAdded = { navController.navigate(Dest.Home.route) }
                )
            }
            composable(Dest.Calendar.route) { PlaceholderScreen("Calendar (demo)") }
            composable(Dest.Docs.route) { PlaceholderScreen("Docs (demo)") }
            composable(Dest.Settings.route) { PlaceholderScreen("Settings (demo)") }
        }
    }
}

@Composable
private fun BottomBar(
    items: List<Dest>,
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        items.forEach { dest ->
            val selected = currentRoute == dest.route
            val icon = when (dest) {
                Dest.Home -> Icons.Filled.Home
                Dest.Calendar -> Icons.Filled.CalendarMonth
                Dest.Add -> Icons.Filled.Add
                Dest.Docs -> Icons.Filled.Description
                Dest.Settings -> Icons.Filled.Settings
            }
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(dest.route) },
                icon = { Icon(icon, contentDescription = dest.label) },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
private fun PlaceholderScreen(title: String) {
    androidx.compose.material3.Surface {
        androidx.compose.material3.Text(
            text = title,
            modifier = Modifier
                .then(Modifier)
                .padding(androidx.compose.ui.unit.dp(24))
        )
    }
}

@Composable
private fun currentRoute(navController: androidx.navigation.NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}
