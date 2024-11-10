package com.puc.quiz.data.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puc.quiz.data.leaderboard.LeaderboardPlayer
import com.puc.quiz.data.leaderboard.LeaderboardRepository
import com.puc.quiz.data.quiz.Question
import kotlinx.coroutines.launch

class AppViewModel(private val repository: LeaderboardRepository, private val context: Context): ViewModel() {
    val leaderboardList = repository.list()
    private val size = "w580"

    private val questions = listOf(
        Question(imageUrl = "https://flagcdn.com/$size/fr.png", answer = "França", options = listOf("Croácia", "Suécia", "Noruega", "França")),
        Question(imageUrl = "https://flagcdn.com/$size/br.png", answer = "Brasil", options = listOf("Finlândia", "Dinamarca", "Grécia", "Brasil")),
        Question(imageUrl = "https://flagcdn.com/$size/de.png", answer = "Alemanha", options = listOf("Egito", "África do Sul", "Portugal", "Alemanha")),
        Question(imageUrl = "https://flagcdn.com/$size/jp.png", answer = "Japão", options = listOf("Turquia", "Índia", "China", "Japão")),
        Question(imageUrl = "https://flagcdn.com/$size/it.png", answer = "Itália", options = listOf("Rússia", "Coreia do Sul", "Nova Zelândia", "Itália")),
        Question(imageUrl = "https://flagcdn.com/$size/ca.png", answer = "Canadá", options = listOf("Filipinas", "Indonésia", "Tailândia", "Canadá")),
        Question(imageUrl = "https://flagcdn.com/$size/au.png", answer = "Austrália", options = listOf("Malásia", "Vietnã", "Suíça", "Austrália")),
        Question(imageUrl = "https://flagcdn.com/$size/mx.png", answer = "México", options = listOf("Áustria", "Irlanda", "Escócia", "México")),
        Question(imageUrl = "https://flagcdn.com/$size/ar.png", answer = "Argentina", options = listOf("Peru", "Colômbia", "Chile", "Argentina")),
        Question(imageUrl = "https://flagcdn.com/$size/es.png", answer = "Espanha", options = listOf("Uruguai", "Venezuela", "Nigéria", "Espanha"))
    )

    fun getQuestions(): List<Question> {
        val _questions = questions.shuffled()
        _questions.forEach{it.options = it.options.shuffled()}
        return _questions
    }

    fun insertLeaderBoardEntry(leaderboardPlayer: LeaderboardPlayer) {
        viewModelScope.launch {
            repository.insertLeaderboardEntry(leaderboardPlayer)
        }
    }
}