package com.manishwarade.recyclerViewDemo.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.manishwarade.recyclerViewDemo.data.model.RestaurantsInfo
import com.manishwarade.recyclerViewDemo.datasource.RestaurantsDataSource.Companion.RADIUS
import com.manishwarade.recyclerViewDemo.ui.adapter.ResturantAdapter
import com.manishwarade.recyclerViewDemo.ui.repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val restoRepo: RestaurantRepository by lazy { RestaurantRepository() }

    lateinit var adapter: ResturantAdapter

    var currentRadius: MutableLiveData<Int> = MutableLiveData(RADIUS)

    val resturants = currentRadius.switchMap {
        restoRepo.findNearByRestaurant().cachedIn(viewModelScope)
    }
}