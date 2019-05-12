package com.rolfrider.tapper.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rolfrider.tapper.score.Score

@Entity(tableName = "score")
class ScoreDB (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val taps: Int,
    val date: String
){
    fun toEntity() = Score(taps, date)

    companion object{
        fun fromEntity(score: Score) = ScoreDB(0, score.taps, score.date)
    }
}
