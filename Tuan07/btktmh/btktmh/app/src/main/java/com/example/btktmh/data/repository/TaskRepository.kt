package com.example.btktmh.data.repository

import com.example.btktmh.data.local.TaskDao
import com.example.btktmh.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    fun observeAllTasks(): Flow<List<TaskEntity>> = dao.getAllTasks()

    suspend fun addTask(title: String, description: String) {
        dao.insertTask(TaskEntity(title = title.trim(), description = description.trim()))
    }
}
