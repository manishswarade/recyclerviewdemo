package com.manishwarade.recyclerViewDemo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.manishwarade.recyclerViewDemo.R
import com.manishwarade.recyclerViewDemo.data.model.Businesses
import com.manishwarade.recyclerViewDemo.data.model.Location
import com.manishwarade.recyclerViewDemo.helper.load
import kotlinx.android.synthetic.main.row_resturants.view.*

class ResturantAdapter: PagingDataAdapter<Businesses, ResturantAdapter.RestaurantViewHoler>(ResturantDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHoler {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_resturants, parent, false)

        return RestaurantViewHoler(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RestaurantViewHoler, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RestaurantViewHoler(view: View): RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Businesses?) {

            item?.let {
                itemView.img_resturant.load(findDataNotNull(item.image_url))

                itemView.text_returant_name.text = findDataNotNull(item.name)

                itemView.text_distance.text = findDataNotNull(findDistancInMeter(item.distance) + findDisplayAddr(item.location))

                itemView.text_status.text = findRestStatus(item.is_closed)

                itemView.text_rating.text = item.rating.toString()
            }
        }

        private fun findDataNotNull(link: String?) = if(link.isNullOrEmpty()) "N/A" else link

        private fun findDistancInMeter(distance: Double) = "${Math.round(distance/100)} M. "

        private fun findDisplayAddr(locations: Location?): String {

            locations?.let {
                it.display_address?.let {
                    return if(it.isEmpty()) "" else it.get(0)
                }
            }

            return ""
        }

        private fun findRestStatus(isOpen: Boolean) = if(isOpen) "Closed Now" else "Currently OPEN"
    }

    class ResturantDiffCallBack : DiffUtil.ItemCallback<Businesses>() {
        override fun areItemsTheSame(oldItem: Businesses, newItem: Businesses): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Businesses, newItem: Businesses): Boolean {
            return oldItem == newItem
        }
    }
}