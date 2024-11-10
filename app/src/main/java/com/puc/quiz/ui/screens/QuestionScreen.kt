package com.puc.quiz.ui.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.puc.quiz.data.quiz.Question
import com.puc.quiz.ui.theme.Green40
import com.puc.quiz.ui.theme.Red40
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun QuestionScreen(question: Question, onNext: (Boolean) -> Unit) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    val clickedStates = remember { mutableStateMapOf<String, Boolean>() }
    var isVisible by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = isVisible,
        exit = slideOutHorizontally(
            targetOffsetX = {fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 1000)
        ) + fadeOut(animationSpec = tween(durationMillis = 1500))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(question.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )

            Text(
                "Qual país é esse?",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            question.options.forEachIndexed { _, option ->
                val isClicked = clickedStates[option] ?: false

                Row(Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .border(
                        border = BorderStroke(
                            1.dp,
                            when {
                                !isClicked -> MaterialTheme.colorScheme.secondary
                                option.equals(question.answer, ignoreCase = true) -> Green40
                                else -> Red40
                            }
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = {
                            onOptionSelected(option)
                            clickedStates[option] = true // Atualizar estado da Row individual

                            val bool = option.equals(question.answer, true)
                            // Chamar onNext após 1 segundo de clique
                            coroutineScope.launch {
                                delay(500)
                                isVisible = false
                                onNext(bool)
                            }
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = {}
                    )
                    Text(
                        text = option,
                        color = when {
                            !isClicked -> MaterialTheme.colorScheme.secondary
                            option.equals(question.answer, ignoreCase = true) -> Green40
                            else -> Red40
                        }
                    )
                }
            }
        }
    }

}
