package com.puc.quiz.data.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.puc.quiz.data.leaderboard.LeaderboardRepository
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory


class AppViewModelFactory(private val repository: LeaderboardRepository, private val context: Context): NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = AppViewModel(repository, context) as T
}