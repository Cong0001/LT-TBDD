package com.example.bktmh.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bktmh.data.local.TaskEntity
import com.example.bktmh.data.repository.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            repository.addTask(
                TaskEntity(title = title, description = description)
            )
        }
    }
}
