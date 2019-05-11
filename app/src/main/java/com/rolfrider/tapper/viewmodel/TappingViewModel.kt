package com.rolfrider.tapper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolfrider.tapper.GameTimer

class TappingViewModel: ViewModel() {



    private val gameTimer = GameTimer(
        {timeLeftLiveData.value = parseMillisToDisplayFormat(it)},
        {
            timeLeftLiveData.value = "00:00"
            endGameLiveData.value = true
        }
        )


    private val timeLeftLiveData = MutableLiveData<String>()
    private val tapsLiveData = MutableLiveData<Int>()
    private val endGameLiveData = MutableLiveData<Boolean>()

    private var tapsCounter = 0

    init {
        tapsLiveData.value = tapsCounter
    }

    fun timeLeft() = timeLeftLiveData
    fun taps() = tapsLiveData
    fun endGame() = endGameLiveData

    fun startGame(){
        gameTimer.start()
    }


    fun tap(){
        tapsCounter++
        tapsLiveData.value = tapsCounter
    }

    private fun parseMillisToDisplayFormat(millis: Long): String{
        val seconds: Int = (millis/1000).toInt()
        val centis: Int = ((millis%1000)/10).toInt()
        return "%02d".format(seconds) + ":" + "%02d".format(centis)
    }

}