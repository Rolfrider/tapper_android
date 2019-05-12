package com.rolfrider.tapper.database

import com.rolfrider.tapper.score.Score
import com.rolfrider.tapper.score.Scores

class RoomScores(private val scoreDao: ScoreDao): Scores {
    override fun add(score: Score): Boolean = scoreDao.addToTopFive(ScoreDB.fromEntity(score))

    override fun get(): List<Score> = scoreDao.getScores().map(ScoreDB::toEntity)
}