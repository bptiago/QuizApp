package com.puc.quiz.data.leaderboard

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaderboardDAO {
    @Query("SELECT * FROM leaderboard ORDER BY score DESC LIMIT 10")
    fun list(): Flow<List<LeaderboardPlayer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeaderboardEntry(leaderboardPlayer: LeaderboardPlayer)
}