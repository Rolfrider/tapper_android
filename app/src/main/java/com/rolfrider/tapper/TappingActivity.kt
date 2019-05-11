package com.rolfrider.tapper


import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rolfrider.tapper.viewmodel.TappingViewModel
import kotlinx.android.synthetic.main.activity_tapping.*

class TappingActivity : AppCompatActivity(){

    private val viewModel: TappingViewModel by lazy { ViewModelProviders.of(this).get(TappingViewModel::class.java)}

    private var touchBegan = false

    private var gameBegan = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tapping)

        viewModel.taps().observe(this, Observer(this::updateTaps))
        viewModel.timeLeft().observe(this, Observer(this::updateTime))
        viewModel.endGame().observe(this, Observer(this::endGame))


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> touchBegan = true
            MotionEvent.ACTION_UP -> {
                if (touchBegan) {
                    viewModel.tap()
                    touchBegan = false
                }
            }
            else -> touchBegan = false
        }

        return true
    }

    override fun onStart() {
        super.onStart()
        if (!gameBegan) {
            viewModel.startGame()
            gameBegan = true
        }
    }


    private fun updateTime(newTime: String){
        timeView.text = newTime
    }

    private fun updateTaps(newTaps: Int){
        tapView.text = "$newTaps"
    }

    private fun endGame(end: Boolean){
        if (end){
            println("END GAME")
        }
    }

}