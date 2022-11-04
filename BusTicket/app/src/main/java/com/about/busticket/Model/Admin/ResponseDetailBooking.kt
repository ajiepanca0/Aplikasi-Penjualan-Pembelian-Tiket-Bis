package com.about.busticket.Model.Admin

data class ResponseDetailBooking(
	val idBooking: String,
	val date: String,
	val bus: String,
	val phone: String,
	val price: String,
	val name: String,
	val destination: String,
	val time: String
)

