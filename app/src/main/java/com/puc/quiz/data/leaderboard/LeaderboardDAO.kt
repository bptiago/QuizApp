package com.puc.quiz.data.leaderboard

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaderboardDAO {
    @Query("SELECT * FROM leaderboard")
    fun list(): Flow<List<LeaderboardPlayer>>
}