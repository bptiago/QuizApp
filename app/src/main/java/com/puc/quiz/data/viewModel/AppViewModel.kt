package com.puc.quiz.data.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puc.quiz.data.leaderboard.LeaderboardPlayer
import com.puc.quiz.data.leaderboard.LeaderboardRepository
import com.puc.quiz.data.quiz.Question
import kotlinx.coroutines.launch

class AppViewModel(private val repository: LeaderboardRepository, private val context: Context): ViewModel() {
    val resultList = repository.list()


    val questions = listOf(
        Question(imageUrl = "", answer = "França"),
        Question(imageUrl = "", answer = "Brasil"),
        Question(imageUrl = "", answer = "Alemanha"),
        Question(imageUrl = "", answer = "Japão"),
        Question(imageUrl = "", answer = "Itália"),
        Question(imageUrl = "", answer = "Canadá"),
        Question(imageUrl = "", answer = "Austrália"),
        Question(imageUrl = "", answer = "México"),
        Question(imageUrl = "", answer = "Argentina"),
        Question(imageUrl = "", answer = "Espanha")
    )

    val options = listOf(
        "Croácia",
        "Suécia",
        "Noruega",
        "Finlândia",
        "Dinamarca",
        "Grécia",
        "Egito",
        "África do Sul",
        "Portugal",
        "Turquia",
        "Índia",
        "China",
        "Rússia",
        "Coreia do Sul",
        "Nova Zelândia",
        "Filipinas",
        "Indonésia",
        "Tailândia",
        "Malásia",
        "Vietnã",
        "Suíça",
        "Áustria",
        "Irlanda",
        "Escócia",
        "Peru",
        "Colômbia",
        "Chile",
        "Uruguai",
        "Venezuela",
        "Nigéria"
    )



    fun insertLeaderBoardEntry(leaderboardPlayer: LeaderboardPlayer) {
        viewModelScope.launch {
            repository.insertLeaderboardEntry(leaderboardPlayer)
        }
    }
}