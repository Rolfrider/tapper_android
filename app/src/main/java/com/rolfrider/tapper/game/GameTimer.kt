package com.rolfrider.tapper.game

import android.os.CountDownTimer

class GameTimer(private val onTickCallback: (Long) -> Unit,
                private val onFinishCallback: () -> Unit){


    private val countDownTimer = object: CountDownTimer(5000, 10){
        override fun onFinish() {
            onFinishCallback()
        }

        override fun onTick(millisUntilFinished: Long) {
            onTickCallback(millisUntilFinished)
        }

    }
    
    fun start(){
        countDownTimer.start()
    }

}