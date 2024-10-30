package com.puc.quiz.data.leaderboard

import kotlinx.coroutines.flow.Flow

class LeaderboardRepositoryImpl(
    private val dao: LeaderboardDAO
) : LeaderboardRepository {
    override fun list(): Flow<List<LeaderboardPlayer>> = dao.list()
}