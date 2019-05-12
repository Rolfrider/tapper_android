package com.rolfrider.tapper.welcome.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rolfrider.tapper.R
import com.rolfrider.tapper.score.Score
import kotlinx.android.synthetic.main.score_recycle_view_item.view.*

class ScoreViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem) {
    private val scoreView = viewItem.scoreView
    private val dateView = viewItem.dateView

    fun bindItem(item: Score){
        scoreView.text = scoreView.context.getString(R.string.tap_score, item.taps)
        dateView.text = item.date
    }
}