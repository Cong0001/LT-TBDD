package com.example.btktmh.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btktmh.data.local.TaskEntity
import com.example.btktmh.data.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TaskListViewModel(
    repository: TaskRepository
) : ViewModel() {

    val tasks: StateFlow<List<TaskEntity>> =
        repository.observeAllTasks()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
