 package com.puc.quiz.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

 @Composable
 fun QuizWelcomeScreen(onQuestionClick: () -> Unit) {
     Box(
         modifier = Modifier.fillMaxSize(),
         contentAlignment = Alignment.Center
     ) {
         Column(horizontalAlignment = Alignment.CenterHorizontally) {
             Text("Welcome to the Quiz!", style = MaterialTheme.typography.titleMedium)

             Spacer(modifier = Modifier.height(16.dp))

             Button(onClick = onQuestionClick) {
                 Text("Start Questions")
             }
         }
     }
 }
