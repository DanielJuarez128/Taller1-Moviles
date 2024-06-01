package com.delgadojuarez.todoappuca.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.delgadojuarez.todoappuca.TasksViewmodel
import com.delgadojuarez.todoappuca.models.Task
import com.delgadojuarez.todoappuca.ui.theme.topbar_color
import com.delgadojuarez.todoappuca.ui.utils.TopBar
import java.time.format.DateTimeFormatter



@Composable
fun MainScreen(
    viewModel: TasksViewmodel,
    onClick: () -> Unit
) {
    val tasks = viewModel.taskList

    androidx.compose.material3.Scaffold(
        topBar = { TopBar("LISTA DE TAREAS") },
        floatingActionButton = { CreateTaskScreen(onSave = { tasks.add(it) }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(tasks) { task ->
                TaskItem(task, {
                    viewModel.saveDataFromSelectedTask(task)
                    onClick
                })
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskItem(
    task: Task,
    onClick: () -> Unit
) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Surface(
        modifier = Modifier.padding(8.dp),
        color = task.color,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = task.iconId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp)
                )
                Column {
                    Text(task.title)
                    Text(task.description)
                    Text("Fecha limite: ${task.deadline.format(dateFormatter)}")
                    Text("Categoria: ${task.category}")
                    if (task.completed){
                        Text(text = "Completada")
                    }else{
                        Text(text = "No completada")
                    }
                }
            }
        }
    }
}