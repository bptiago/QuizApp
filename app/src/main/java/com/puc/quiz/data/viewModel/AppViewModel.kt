package com.puc.quiz.data.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puc.quiz.data.leaderboard.LeaderboardPlayer
import com.puc.quiz.data.leaderboard.LeaderboardRepository
import kotlinx.coroutines.launch

class AppViewModel(private val repository: LeaderboardRepository, private val context: Context): ViewModel() {
    val resultList = repository.list()

    fun insertLeaderBoardEntry(leaderboardPlayer: LeaderboardPlayer) {
        viewModelScope.launch {
            repository.insertLeaderboardEntry(leaderboardPlayer)
        }
    }
}