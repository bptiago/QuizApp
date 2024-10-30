package com.puc.quiz.data.leaderboard

import kotlinx.coroutines.flow.Flow


interface LeaderboardRepository {
    fun list(): Flow<List<LeaderboardPlayer>>
}