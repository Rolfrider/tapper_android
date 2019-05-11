package com.rolfrider.tapper


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_tapping.*

class TappingActivity : AppCompatActivity(){

    private val viewModel: TappingViewModel by lazy { ViewModelProviders.of(this).get(TappingViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tapping)

        viewModel.taps().observe(this, Observer(this::updateTaps))
        viewModel.timeLeft().observe(this, Observer(this::updateTime))
    }


    private fun updateTime(newTime: String){
        timeView.text = newTime
    }

    private fun updateTaps(newTaps: String){
        tapView.text = newTaps
    }
}