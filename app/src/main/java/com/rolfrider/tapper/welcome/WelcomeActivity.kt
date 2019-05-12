package com.rolfrider.tapper.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rolfrider.tapper.R
import com.rolfrider.tapper.game.TappingActivity
import com.rolfrider.tapper.score.Score
import com.rolfrider.tapper.viewmodel.ViewModelFactory
import com.rolfrider.tapper.viewmodel.WelcomeViewModel
import com.rolfrider.tapper.welcome.recycler.ScoreAdapter
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private val viewModel: WelcomeViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(application))[WelcomeViewModel::class.java]
    }

    private val adapter: ScoreAdapter by lazy { ScoreAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        scoresRecycleView.adapter = adapter

        viewModel.scores().observe(this, Observer(this::handleScores))


    }

    override fun onStart() {
        super.onStart()
        viewModel.getScores()
    }

    private fun handleScores(scores: List<Score>){
        adapter.items = scores
        adapter.notifyDataSetChanged()
    }

    fun handleButtonClick(view: View){
        Intent(this, TappingActivity::class.java)
            .let { startActivity(it) }
    }
}
