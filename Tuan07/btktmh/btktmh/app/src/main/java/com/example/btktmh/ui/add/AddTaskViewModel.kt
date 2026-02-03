package com.example.btktmh.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btktmh.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AddTaskUiState(
    val title: String = "",
    val description: String = "",
    val error: String? = null,
    val isSaving: Boolean = false
)

class AddTaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState: StateFlow<AddTaskUiState> = _uiState.asStateFlow()

    fun onTitleChange(v: String) {
        _uiState.value = _uiState.value.copy(title = v, error = null)
    }

    fun onDescriptionChange(v: String) {
        _uiState.value = _uiState.value.copy(description = v, error = null)
    }

    fun addTask(onDone: () -> Unit) {
        val title = _uiState.value.title.trim()
        val desc = _uiState.value.description.trim()

        if (title.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Vui lòng nhập Task")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSaving = true, error = null)
            repository.addTask(title = title, description = desc)
            _uiState.value = AddTaskUiState()
            onDone()
        }
    }
}
