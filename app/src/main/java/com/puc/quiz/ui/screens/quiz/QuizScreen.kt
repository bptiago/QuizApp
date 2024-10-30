package com.puc.quiz.ui.screens.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuizScreen(
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Imagem")
        QuizOptions()
    }
}

@Composable
fun QuizOptions() {
    val options = listOf("Lorem ipsulom", "Janes vadis oto nunp")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }

    options.forEach { option ->
        Row(Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(10.dp))
            .selectable(
                selected = (option == selectedOption),
                onClick = { onOptionSelected(option) }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = (option == selectedOption),
                onClick = { onOptionSelected(option) }
            )
            Text(text = option, color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Composable
@Preview
fun QuizScreenPreview() {
    QuizScreen()
}