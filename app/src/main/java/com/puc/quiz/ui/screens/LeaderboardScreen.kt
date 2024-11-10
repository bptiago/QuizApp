package com.puc.quiz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.puc.quiz.data.leaderboard.LeaderboardPlayer
import com.puc.quiz.ui.theme.Red40

@Composable
fun LeaderboardScreen(
    leaderboard: List<LeaderboardPlayer>,
    onSaveScore: (LeaderboardPlayer) -> Unit,
    onBack: () -> Unit,
    score: Int)
{
    var playerName by remember { mutableStateOf("") }
    var warnText by remember { mutableStateOf("") }
    var isSaved by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Seu score: $score",
            style = MaterialTheme.typography.titleLarge,
            textDecoration = TextDecoration.Underline
        )
        
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.width(200.dp)
            ){
                TextField(
                    value = playerName,
                    onValueChange = {playerName = it},
                    placeholder = { Text(text = "Insira seu nome") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    singleLine = true
                )

                Text(
                    text = warnText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Red40
                )
            }

            Spacer(modifier = Modifier.size(15.dp))

            Button(onClick = {
                if (score == 0) {
                    warnText = "Seu score é zero! Tente novamente com um score maior."
                } else if (playerName.isEmpty()) {
                    warnText = "Inserir um nome válido!"
                }
                else if(isSaved) {
                    warnText = "Você já salvou o seu score!"
                } else {
                    warnText = ""
                    val player = LeaderboardPlayer(null, playerName, score)
                    isSaved = true
                    onSaveScore(player)
                }
            }) {
                Text(text = "Salvar")
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "Leaderboard",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                itemsIndexed(leaderboard) {index, item -> LeaderboardItem(index, item) }
            }

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedButton(onClick = onBack) {
                Text(text = "Voltar ao Início")
            }

            Spacer(modifier = Modifier.size(40.dp))
        }

    }
}

@Composable
fun LeaderboardItem(index: Int, entry: LeaderboardPlayer) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Text(
            text = (index + 1).toString(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(32.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = entry.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "${entry.score} pts",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}