package com.delgadojuarez.todoappuca.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.delgadojuarez.todoappuca.ui.theme.topbar_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar( title: String) {
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
                text = title,
                textAlign = TextAlign.Center,
                color = Color.White // Color del texto
            )
        }
    }
}