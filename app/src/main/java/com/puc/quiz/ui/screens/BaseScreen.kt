package com.puc.quiz.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.puc.quiz.data.leaderboard.LeaderboardPlayer
import com.puc.quiz.data.viewModel.AppViewModel
import com.puc.quiz.data.viewModel.AppViewModelFactory
import com.puc.quiz.ui.screens.leaderboard.LeaderboardScreen

@Composable
fun BaseScreen(
    factory: AppViewModelFactory,
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel(factory = factory)
) {
    val leaderboardData by appViewModel.resultList.collectAsState(initial = emptyList())
    LeaderboardScreen(leaderboardData)
}