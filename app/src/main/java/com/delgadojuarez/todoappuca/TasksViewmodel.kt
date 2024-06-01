package com.delgadojuarez.todoappuca

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delgadojuarez.todoappuca.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TasksViewmodel: ViewModel () {
    private val _tasksList = MutableStateFlow<List<Task>>(emptyList())
    val taskList = _tasksList.asStateFlow()
    private val _taskInfo = MutableStateFlow(Task())
    val taskInfo = _taskInfo.asStateFlow()

    fun saveDataFromSelectedTask(taskSelected: Task) {
        viewModelScope.launch {
            _taskInfo.value = taskSelected
        }
    }
}