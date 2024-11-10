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
 fun QuizWelcomeScreen(onQuestionClick: () -> Unit, onBack: () -> Unit) {
     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(horizontal = 16.dp),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {

         Image(
             painterResource(R.drawable.hand),
             "Imagem de 'olá'",
             contentScale = ContentScale.Fit
         )

         Spacer(modifier = Modifier.size(15.dp))

         Text(
             text = "Antes de começarmos...",
             style = MaterialTheme.typography.headlineMedium,
             fontWeight = FontWeight.SemiBold,
             modifier = Modifier.padding(bottom = 15.dp)
         )

         Text(
             text = "Você será questionado sobre alguns países. Nesse sentido, aparecerá a "
             + "imagem de uma bandeira na sua tela e você deverá indicar a opção com o nome do país que possui a bandeira correspondente."
             + " Preparado?",
             style = MaterialTheme.typography.bodyLarge,
             textAlign = TextAlign.Justify,
             modifier = Modifier.padding(horizontal = 15.dp)
         )

         Spacer(modifier = Modifier.height(30.dp))

         Button(onClick = onQuestionClick) {
             Text("Começar")
         }

         Spacer(modifier = Modifier.size(10.dp))

         OutlinedButton(onClick = onBack) {
             Text(text = "Voltar")
         }
     }
 }
