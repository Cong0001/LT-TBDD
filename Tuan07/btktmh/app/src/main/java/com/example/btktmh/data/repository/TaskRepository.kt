package com.example.bktmh.data.repository

import com.example.bktmh.data.local.TaskDao
import com.example.bktmh.data.local.TaskEntity

class TaskRepository(private val dao: TaskDao) {

    val tasks = dao.getAllTasks()

    suspend fun addTask(task: TaskEntity) {
        dao.insertTask(task)
    }
}
