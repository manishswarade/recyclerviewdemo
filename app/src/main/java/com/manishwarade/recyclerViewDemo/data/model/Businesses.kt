package com.manishwarade.recyclerViewDemo.data.model

data class Businesses (


	var id: String? = "",


	var alias: String? = "",



	var name: String? = "",


	var image_url: String? = "",


	var is_closed: Boolean = false,


	var url: String? = "",


	var review_count: Int = 0,


	var categories: List<Categories>? = ArrayList(),


	var rating: Double = 0.0,


	var coordinates: Coordinates? = Coordinates(),


	var transactions: List<String>? = ArrayList(),


	var price: String? = "",


	var location: Location? = Location(),


	var phone: String? = "",


	var display_phone: String? = "",


	var distance: Double = 0.0,
)