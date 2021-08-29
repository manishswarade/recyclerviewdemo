package com.manishwarade.recyclerViewDemo.ui.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.manishwarade.recyclerViewDemo.R
import com.manishwarade.recyclerViewDemo.datasource.RestaurantsDataSource.Companion.RADIUS
import com.manishwarade.recyclerViewDemo.helper.ConnectionLiveData
import com.manishwarade.recyclerViewDemo.helper.NetworkHelper.verifyAvailableNetwork
import com.manishwarade.recyclerViewDemo.ui.adapter.ResturantAdapter
import com.manishwarade.recyclerViewDemo.ui.adapter.ResturantLoadStateAdapter
import com.manishwarade.recyclerViewDemo.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectionLiveData = ConnectionLiveData(this)
        initUi()
        handleEvent()
        checkInternet()
    }

    private fun initUi() {

        mainViewModel.adapter = ResturantAdapter()

        recycler.apply {
            this.setHasFixedSize(true)
            this.adapter = mainViewModel.adapter.withLoadStateHeaderAndFooter(
                header = ResturantLoadStateAdapter(),
                footer = ResturantLoadStateAdapter()
            )
        }

        mainViewModel.resturants.observe(this, {
            lifecycleScope.launch {
                mainViewModel.adapter.submitData(it)
            }
        })
    }

    private fun handleEvent() {

        connectionLiveData.observe(this, {
            if (!it)
                showInternetConnectionMessage(VISIBLE)
            else {
                showInternetConnectionMessage(GONE)
                mainViewModel.currentRadius.value = RADIUS
            }
        })
    }

    private fun showInternetConnectionMessage(ivVisible: Int) {
        text_network.visibility = ivVisible
    }

    private fun checkInternet() {
        if(!verifyAvailableNetwork(this)) showInternetConnectionMessage(VISIBLE)
    }
}