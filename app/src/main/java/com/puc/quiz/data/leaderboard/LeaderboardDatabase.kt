package com.puc.quiz.data.leaderboard

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LeaderboardPlayer::class], version = 2, exportSchema = false)
abstract class LeaderboardDatabase : RoomDatabase() {
    abstract val leaderboardDao: LeaderboardDAO

    companion object {
        @Volatile
        private var INSTANCE: LeaderboardDatabase? = null

        fun getInstance(context: Context): LeaderboardDatabase {
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LeaderboardDatabase::class.java,
                    "quiz_db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}