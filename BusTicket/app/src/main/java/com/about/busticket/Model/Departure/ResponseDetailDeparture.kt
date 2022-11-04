package com.about.busticket.Model.Departure

import com.google.gson.annotations.SerializedName

data class ResponseDetailDeparture(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("id_departure")
	val idDeparture: String,

	@field:SerializedName("name_bus")
	val nameBus: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("destination")
	val destination: String,

	@field:SerializedName("time")
	val time: String
)
