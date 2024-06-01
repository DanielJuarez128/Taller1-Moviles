package com.delgadojuarez.todoappuca.screens

import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.delgadojuarez.todoappuca.TasksViewmodel
import com.delgadojuarez.todoappuca.models.Task
import com.delgadojuarez.todoappuca.ui.theme.topbar_color
import com.delgadojuarez.todoappuca.ui.utils.TopBar

@Composable
fun DetailsTaskScreen(
    viewModel: TasksViewmodel
){
    androidx.compose.material3.Scaffold(
        topBar = { TopBar(title = "DETALLES DE LA TAREA") },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            taskDetails(task = viewModel.taskInfo.value)
        }
    }
}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun taskDetails(
    task: Task
) {
    var editing by remember { mutableStateOf(false) }
    var copyTitle by remember { mutableStateOf(task.title) }
    var copyDescription by remember { mutableStateOf(task.description) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = task.color
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (editing) {
                    OutlinedTextField(
                        value = copyTitle,
                        onValueChange = { copyTitle = it }
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .weight(0.95f),
                        text = title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Column {
                if (editing) {
                    OutlinedTextField(
                        value = copyDescription,
                        onValueChange = { copyDescription = it }
                    )
                } else {
                    Text(text = description)
                }
                Text("Fecha limite: ${task.deadline.format(dateFormatter)}")
                Text("Categoria: ${task.category}")
            }
            Box(
                modifier = Modifier
            ){
                Switch(
                    checked = task.completed,
                    onCheckedChange = {task.completed = it}
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (!editing){
                    Text(
                        modifier = Modifier
                            .drawBehind {
                                val strokeWidthPx = 1.dp.toPx()
                                val verticalOffset = size.height - 2.sp.toPx()
                                drawLine(
                                    color = Color.Gray,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            }
                            .clickable {
                                task.title = copyTitle
                                task.description = copyDescription
                                editing = !editing
                            },
                        color = MaterialTheme.colorScheme.secondary,
                        text = "Editar post",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }else{
                    Text(
                        modifier = Modifier
                            .drawBehind {
                                val strokeWidthPx = 1.dp.toPx()
                                val verticalOffset = size.height - 2.sp.toPx()
                                drawLine(
                                    color = Color.Gray,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            }
                            .clickable {
                                editing = !editing
                            },
                        color = MaterialTheme.colorScheme.secondary,
                        text = "Guardar post",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

