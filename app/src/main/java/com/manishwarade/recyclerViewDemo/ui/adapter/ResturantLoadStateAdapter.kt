package com.manishwarade.recyclerViewDemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manishwarade.recyclerViewDemo.R
import kotlinx.android.synthetic.main.resturant_load_state_footer.view.*

class ResturantLoadStateAdapter :
    LoadStateAdapter<ResturantLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.resturant_load_state_footer, parent, false)
        return LoadStateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {


        fun bind(loadState: LoadState) {
            itemView.apply {
                this.progress_bar.isVisible = loadState is LoadState.Loading
            }
        }
    }
}