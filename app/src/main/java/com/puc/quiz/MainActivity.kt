package com.puc.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.puc.quiz.data.leaderboard.LeaderboardDatabase
import com.puc.quiz.data.leaderboard.LeaderboardPlayer
import com.puc.quiz.data.leaderboard.LeaderboardRepositoryImpl
import com.puc.quiz.data.viewModel.AppViewModelFactory
import com.puc.quiz.ui.screens.BaseScreen
import com.puc.quiz.ui.theme.QuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = LeaderboardDatabase.getInstance(application).leaderboardDao
        val repository = LeaderboardRepositoryImpl(dao)
        val factory = AppViewModelFactory(repository, this)

        setContent {
            QuizTheme {
                Surface {
                    BaseScreen(factory = factory)
                }
            }
        }
    }
}

