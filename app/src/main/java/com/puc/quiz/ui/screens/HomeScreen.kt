package com.puc.quiz.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.puc.quiz.R

@Composable
fun HomeScreen(onStartQuiz: () -> Unit, onSeeLeaderboard: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.star),
            "Imagem de 'olá'",
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.size(15.dp))

        Text(
            text = "Bem-vindo!",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Teste seu conhecimento com o nosso quiz. Desafie-se com perguntas sobre alguns países do mundo e veja o quanto você sabe!",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onStartQuiz,
            ) {
                Text(text = "Começar Quiz")
            }

            Spacer(modifier = Modifier.size(10.dp))
            
            OutlinedButton(
                onClick = onSeeLeaderboard,
            ) {
                Text(text = "Ver Leaderboard")
            }
        }

    }
}
