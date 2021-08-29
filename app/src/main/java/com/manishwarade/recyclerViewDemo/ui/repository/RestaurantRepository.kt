package com.manishwarade.recyclerViewDemo.ui.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.manishwarade.recyclerViewDemo.datasource.RestaurantsDataSource
import com.manishwarade.recyclerViewDemo.datasource.RestaurantsDataSource.Companion.LIMIT

class RestaurantRepository {

    fun findNearByRestaurant() = Pager(
        config = PagingConfig(
            pageSize = LIMIT,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            RestaurantsDataSource()
        }
    ).liveData
}