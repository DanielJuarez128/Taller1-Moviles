package com.delgadojuarez.todoappuca.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class Task @RequiresApi(Build.VERSION_CODES.O) constructor(
    val title: String,
    val description: String,
    val deadline: LocalDate,
    val iconId: Int,
    val category: String,
    val color: androidx.compose.ui.graphics.Color,
    val creationDate: LocalDate = LocalDate.now(),
    val completed: Boolean
)