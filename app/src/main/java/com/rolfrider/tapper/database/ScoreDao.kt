package com.rolfrider.tapper.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class ScoreDao {

    @Insert
    abstract fun addScore(score: ScoreDB)

    @Delete
    abstract fun deleteScore(score: ScoreDB)

    @Query("SELECT * FROM score ORDER BY taps")
    abstract fun getScores(): List<ScoreDB>

    fun addToTopFive(score: ScoreDB): Boolean {
        val topFive = getScores()
        return when {
            topFive.size < 5 -> {
                addScore(score)
                true
            }
            topFive.last().taps < score.taps -> {
                deleteScore(topFive.last())
                addScore(score)
                true
            }
            else -> false
        }

    }
}