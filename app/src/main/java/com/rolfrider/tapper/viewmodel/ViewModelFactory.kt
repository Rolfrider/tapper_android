package com.rolfrider.tapper.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rolfrider.tapper.database.RoomScores
import com.rolfrider.tapper.database.ScoreDatabase
import com.rolfrider.tapper.score.Scores

class ViewModelFactory(application: Application): ViewModelProvider.Factory {

    private val roomScores: Scores

    init {
        ScoreDatabase.initIfNeeded(application)
        roomScores = RoomScores(ScoreDatabase.INSTANCE.scores())
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TappingViewModel::class.java) -> {
                TappingViewModel(roomScores) as T
            }
            else -> throw IllegalArgumentException("Unknown view model ${modelClass.simpleName}")
        }
    }
}