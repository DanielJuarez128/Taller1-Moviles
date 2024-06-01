package com.delgadojuarez.todoappuca.screens

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import com.delgadojuarez.todoappuca.R
import com.delgadojuarez.todoappuca.models.Task
import com.delgadojuarez.todoappuca.ui.theme.bandera_color
import com.delgadojuarez.todoappuca.ui.theme.campana_color
import com.delgadojuarez.todoappuca.ui.theme.carita_feliz_color
import com.delgadojuarez.todoappuca.ui.theme.lapiz_color
import com.delgadojuarez.todoappuca.ui.theme.luna_color
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val iconColorMap = mapOf(
    R.drawable.bandera to bandera_color,
    R.drawable.campana to campana_color,
    R.drawable.carita_feliz to carita_feliz_color,
    R.drawable.luna to luna_color,
    R.drawable.lapiz_task to lapiz_color

)



@Composable
fun RoundedGrayFloatingButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.padding(16.dp),

        contentColor = Color.White,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit"
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(onSave: (Task) -> Unit) {
    val showDialog = remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf(LocalDate.now()) }
    var selectedIconId by remember { mutableStateOf<Int?>(null) }
    var category by remember { mutableStateOf("") }
    val isTitleValid by derivedStateOf { title.isNotBlank() }
    val isCategoryValid by derivedStateOf { category.isNotBlank() }

    val icons = listOf(
        R.drawable.bandera,
        R.drawable.campana,
        R.drawable.carita_feliz,
        R.drawable.lapiz_task,
        R.drawable.luna
    )

    val context = LocalContext.current
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                deadline = LocalDate.of(year, month + 1, dayOfMonth)
            },
            deadline.year,
            deadline.monthValue - 1,
            deadline.dayOfMonth
        )
    }

    RoundedGrayFloatingButton(onClick = { showDialog.value = true })

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = true)
        ) {
            Surface(modifier = Modifier.wrapContentSize(), color = Color.White) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BasicTextField(
                        value = title,
                        onValueChange = { title = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions.Default,
                        decorationBox = { innerTextField ->
                            if (title.isBlank()) Text("Titulo", color = Color.Gray)
                            innerTextField()
                        }
                    )
                    BasicTextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            if (description.isBlank()) Text("Descripcion", color = Color.Gray)
                            innerTextField()
                        }
                    )

                    Button(onClick = { datePickerDialog.show() }) {
                        Text("Seleccionar fecha")
                    }
                    Text("Fecha limite: ${deadline.format(dateFormatter)}")

                    // Icon Selection
                    Text("-Seleccionar icono-")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        icons.forEach { iconId ->
                            Icon(
                                imageVector = ImageVector.vectorResource(id = iconId),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(if (selectedIconId == iconId) Color.Gray else Color.Transparent, CircleShape)
                                    .padding(8.dp)
                                    .clickable { selectedIconId = iconId }
                            )
                        }
                    }

                    BasicTextField(
                        value = category,
                        onValueChange = { category = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions.Default,
                        decorationBox = { innerTextField ->
                            if (category.isBlank()) Text("Categoria", color = Color.Gray)
                            innerTextField()
                        }
                    )
                    Button(
                        onClick = {
                            val task = Task(
                                title = title,
                                description = description,
                                deadline = deadline,
                                iconId = selectedIconId ?: R.drawable.bandera,  // Default icon if none selected
                                category = category,
                                color = iconColorMap[selectedIconId ?: R.drawable.bandera] ?: Color.Blue  // Default color
                            )
                            onSave(task)
                            showDialog.value = false
                        },
                        enabled = isTitleValid && isCategoryValid
                    ) {
                        Text("Guardar tarea")
                    }
                }
            }
        }
    }
}