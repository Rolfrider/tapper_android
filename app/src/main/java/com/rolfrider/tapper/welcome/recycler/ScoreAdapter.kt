package com.rolfrider.tapper.welcome.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rolfrider.tapper.R
import com.rolfrider.tapper.score.Score

class ScoreAdapter(
    var items: List<Score>
): RecyclerView.Adapter<ScoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.score_recycle_view_item,
            parent, false)

        return ScoreViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}