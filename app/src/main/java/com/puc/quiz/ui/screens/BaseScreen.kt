package com.puc.quiz.ui.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
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
        composable(
            "home",
            enterTransition = {
                when (initialState.destination.route) {
                    "quiz" -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )

                    "leaderboard" -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(1000)
                    )

                    else -> {null}
                }
            },
            exitTransition = {
                when (this.targetState.destination.route) {
                    "quiz" -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(1000)
                    )

                    "leaderboard" -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )

                    else -> {null}
                }
            }
        ) {
            score = 0
            HomeScreen(
                onStartQuiz = {navController.navigate("quiz")},
                onSeeLeaderboard = {navController.navigate("leaderboard")}
            )
        }

        composable(
            "quiz",
            enterTransition = {slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(1000))},
            exitTransition = {
                when (this.targetState.destination.route) {
                    "question/0" -> fadeOut(animationSpec = tween(500))

                    "home" -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )

                    else -> {null}
                }
            }
        ) {
            QuizWelcomeScreen(
                onQuestionClick = {navController.navigate("question/0")},
                onBack = {navController.navigate("home")}
            )
        }

        composable(
            "leaderboard",
            enterTransition = {
                when (initialState.destination.route) {
                    "question/9" -> scaleIn(initialScale = 0f, animationSpec = tween(1200))

                    "home" -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )

                    else -> {null}
                }
            },
            exitTransition = {slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(1000))}
        ) {
            LeaderboardScreen(
                leaderboard = leaderboardData,
                onSaveScore = { player -> appViewModel.insertLeaderBoardEntry(player) },
                onBack = {navController.navigate("home")},
                score = score
            )
        }

        questions.forEachIndexed { index, _ ->
            composable(
                "question/$index",
                enterTransition = { fadeIn(animationSpec = tween(1500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
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