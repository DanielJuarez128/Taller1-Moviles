package com.delgadojuarez.todoappuca.ui.theme.Screens

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.delgadojuarez.todoappuca.ui.theme.topbar_color

import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    Surface(
        color = topbar_color,
        contentColor = Color.White,
        modifier = Modifier
            .height(95.dp)
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "LISTA DE TAREAS",
                textAlign = TextAlign.Center,
                color = Color.White // Color del texto
            )
        }
    }
}

@Composable
fun MainScreen() {
    val tasks = remember { mutableStateListOf<Task>() }

    androidx.compose.material3.Scaffold(
        topBar = { TopBar() },
        floatingActionButton = { CreateTaskScreen(onSave = { tasks.add(it) }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskItem(task: Task) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Surface(
        modifier = Modifier.padding(8.dp),
        color = task.color,
        shape = RoundedCornerShape(8.dp)
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
                    modifier = Modifier.size(48.dp).padding(end = 8.dp)
                )
                Column {
                    Text(task.title)
                    Text(task.description)
                    Text("Fecha limite: ${task.deadline.format(dateFormatter)}")
                    Text("Categoria: ${task.category}")
                }
            }
        }
    }
}