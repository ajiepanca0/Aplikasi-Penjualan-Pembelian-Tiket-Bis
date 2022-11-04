package com.about.busticket.Model.Admin

import com.google.gson.annotations.SerializedName

data class ResponseAdmin( //sebagai data class dari hasil responadmin

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)
