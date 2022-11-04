package com.about.busticket.Model.Admin

import com.google.gson.annotations.SerializedName

data class ResponseBoMe(

	@field:SerializedName("BoMe")
	val boMe: List<BoMeItem>
)

data class BoMeItem(

	@field:SerializedName("id_booking")
	val idBooking: String,

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("bus")
	val bus: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("destination")
	val destination: String,

	@field:SerializedName("time")
	val time: String
)
