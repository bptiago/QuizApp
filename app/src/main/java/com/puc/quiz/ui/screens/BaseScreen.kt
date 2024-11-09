package com.puc.quiz.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.puc.quiz.data.leaderboard.LeaderboardPlayer
import com.puc.quiz.data.viewModel.AppViewModel
import com.puc.quiz.data.viewModel.AppViewModelFactory

@Composable
fun BaseScreen(
    factory: AppViewModelFactory,
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel(factory = factory)
) {
    val navController = rememberNavController()
    val questions = appViewModel.getQuestions()
    val leaderboardData by appViewModel.leaderboardList.collectAsState(initial = emptyList())
    var score by remember { mutableIntStateOf(0) }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onStartQuiz = {navController.navigate("quiz")})
        }

        composable("quiz") {
            QuizWelcomeScreen(
                onQuestionClick = {navController.navigate("question/0")}
            )
        }

        composable("leaderboard") {
            LeaderboardScreen(
                leaderboard = leaderboardData,
                onSaveScore = { player ->
                    appViewModel.insertLeaderBoardEntry(player)
                    navController.navigate("home") },
                score = score
            )
        }

        questions.forEachIndexed { index, _ ->
            composable("question/$index") {
                QuestionScreen(question = questions[index],
                    onNext = { isCorrect ->
                        if (isCorrect) score += 10

                        if (index < questions.size - 1)
                            navController.navigate("question/${index + 1}")
                        else
                            navController.navigate("leaderboard")
                    }
                )
            }
        }
    }

}