package com.example.bktmh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.bktmh.data.local.AppDatabase
import com.example.bktmh.data.repository.TaskRepository
import com.example.bktmh.ui.add.AddTaskScreen
import com.example.bktmh.ui.add.AddTaskViewModel
import com.example.bktmh.ui.list.TaskListScreen
import com.example.bktmh.ui.list.TaskListViewModel
import com.example.bktmh.ui.theme.BktmhTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getInstance(this)
        val repository = TaskRepository(db.taskDao())

        setContent {

            BktmhTheme {

                val navController = rememberNavController()

                val listVM: TaskListViewModel = viewModel {
                    TaskListViewModel(repository)
                }

                val addVM: AddTaskViewModel = viewModel {
                    AddTaskViewModel(repository)
                }

                NavHost(
                    navController = navController,
                    startDestination = "list"
                ) {

                    composable("list") {
                        TaskListScreen(navController, listVM)
                    }

                    composable("add") {
                        AddTaskScreen(navController, addVM)
                    }
                }
            }
        }
    }
}
