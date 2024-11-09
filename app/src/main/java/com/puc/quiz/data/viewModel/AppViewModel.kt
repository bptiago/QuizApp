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

    private val questions = listOf(
        Question(imageUrl = "", answer = "França", options = listOf("Croácia", "Suécia", "Noruega", "França")),
        Question(imageUrl = "", answer = "Brasil", options = listOf("Finlândia", "Dinamarca", "Grécia", "Brasil")),
        Question(imageUrl = "", answer = "Alemanha", options = listOf("Egito", "África do Sul", "Portugal", "Alemanha")),
        Question(imageUrl = "", answer = "Japão", options = listOf("Turquia", "Índia", "China", "Japão")),
        Question(imageUrl = "", answer = "Itália", options = listOf("Rússia", "Coreia do Sul", "Nova Zelândia", "Itália")),
        Question(imageUrl = "", answer = "Canadá", options = listOf("Filipinas", "Indonésia", "Tailândia", "Canadá")),
        Question(imageUrl = "", answer = "Austrália", options = listOf("Malásia", "Vietnã", "Suíça", "Austrália")),
        Question(imageUrl = "", answer = "México", options = listOf("Áustria", "Irlanda", "Escócia", "México")),
        Question(imageUrl = "", answer = "Argentina", options = listOf("Peru", "Colômbia", "Chile", "Argentina")),
        Question(imageUrl = "", answer = "Espanha", options = listOf("Uruguai", "Venezuela", "Nigéria", "Espanha"))
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