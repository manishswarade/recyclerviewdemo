package com.manishwarade.recyclerViewDemo.network

import com.manishwarade.recyclerViewDemo.data.model.RestaurantsInfo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val API_KEY = "XPFgzKwZGK1yqRxHi0d5xsARFOLpXIvccQj5jekqTnysweGyoIfVUHcH2tPfGq5Oc9kwKHPkcOjk2d1Xobn7aTjOFeop8x41IUfVvg2Y27KiINjYPADcE7Qza0RkX3Yx"
        const val BASE_URL = "https://api.yelp.com/v3/"
    }


    @Headers("Content-Type: application/json", "Authorization: Bearer $API_KEY")
    @GET("businesses/search")
    suspend fun findNearByResturants(@Query("term") term: String,
                             @Query("location") location: String,
                             @Query("radius") radius: Int,
                             @Query("sort_by") sort_by: String,
                             @Query("limit") limit: Int): RestaurantsInfo
}