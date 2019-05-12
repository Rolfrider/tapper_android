package com.rolfrider.tapper


import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rolfrider.tapper.viewmodel.TappingViewModel
import kotlinx.android.synthetic.main.activity_tapping.*

class TappingActivity : AppCompatActivity(){

    private val viewModel: TappingViewModel by lazy { ViewModelProviders.of(this).get(TappingViewModel::class.java)}

    private var touchBegan = false

    private var gameBegan = false

    private var gameEnd = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tapping)

        viewModel.taps().observe(this, Observer(this::updateTaps))
        viewModel.timeLeft().observe(this, Observer(this::updateTime))
        viewModel.endGame().observe(this, Observer(this::endGame))

        countdown_animation.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                Log.d("Animation:", "Repeat")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.d("Animation:", "End")
                countdown_animation.visibility = View.GONE
                playAnimation()

            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.d("Animation:", "Cancel")
            }

            override fun onAnimationStart(animation: Animator?) {
                Log.d("Animation:", "Start")
            }

        })

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(gameBegan && !gameEnd) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> touchBegan = true
                MotionEvent.ACTION_UP -> {
                    if (touchBegan) {
                        viewModel.tap()
                        touchBegan = false
                    }
                }
                else -> touchBegan = false
            }
        }
        return true
    }

    override fun onStart() {
        super.onStart()



        if (!gameBegan) {
            countdown_animation.playAnimation()
        }
    }

    private fun playAnimation(){
        playView.visibility = View.VISIBLE
        val animator = AnimatorInflater.loadAnimator(this, R.animator.scale_up_animatior)
        animator.setTarget(playView)
        animator.doOnEnd {
            playView.visibility = View.GONE
            tapView.visibility = View.VISIBLE
            timeView.visibility = View.VISIBLE
            viewModel.startGame()
            gameBegan = true
        }
        animator.start()
    }

    private fun updateTime(newTime: String){
        timeView.text = newTime
    }

    private fun updateTaps(newTaps: Int){
        tapView.text = "$newTaps"
    }

    private fun endGame(end: Boolean){
        if (end){
            gameEnd = true
        }
    }

}