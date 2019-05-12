package com.rolfrider.tapper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolfrider.tapper.score.Score
import com.rolfrider.tapper.score.Scores

class WelcomeViewModel(private val scores: Scores): ViewModel() {


    private val scoresLiveData = MutableLiveData<List<Score>>()

    fun scores() = scoresLiveData

    fun getScores() {
        scoresLiveData.value = scores.get()
    }
}