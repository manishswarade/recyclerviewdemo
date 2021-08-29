package com.manishwarade.recyclerViewDemo.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.manishwarade.recyclerViewDemo.data.model.Businesses
import com.manishwarade.recyclerViewDemo.network.RetrofitBuilder
import retrofit2.HttpException
import java.io.IOException

class RestaurantsDataSource : PagingSource<Int, Businesses>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Businesses> {

        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = RetrofitBuilder.apiService.findNearByResturants(TERM, LOCATION, RADIUS, SORT_BY, LIMIT)
            val businesses = response.businesses

            LoadResult.Page(
                data = businesses,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (businesses.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val TERM = "restaurants"
        private const val LOCATION = "NYC"
        var RADIUS = 500
        private const val SORT_BY = "distance"
        const val LIMIT = 15
    }

    override fun getRefreshKey(state: PagingState<Int, Businesses>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}