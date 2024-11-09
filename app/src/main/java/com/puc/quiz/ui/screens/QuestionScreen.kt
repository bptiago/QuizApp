package com.puc.quiz.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.puc.quiz.data.quiz.Question

@Composable
fun QuestionScreen(question: Question, onNext: () -> Unit) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Qual país é esse?", style = MaterialTheme.typography.titleSmall)

        Spacer(Modifier.height(16.dp))

        question.options.forEachIndexed { _, option ->
            Row(Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .border(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                    shape = RoundedCornerShape(10.dp)
                )
                .selectable(
                    selected = (option == selectedOption),
                    onClick = {
                        // TODO: Lógica para verificar se a resposta é correta. Se certo, adicionar pontos ao score do usuário
                        onOptionSelected(option)
                        onNext()
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = {
                        // TODO: Lógica para verificar se a resposta é correta. Se certo, adicionar pontos ao score do usuário
                        onOptionSelected(option)
                        onNext()
                    }
                )
                Text(text = option, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}
