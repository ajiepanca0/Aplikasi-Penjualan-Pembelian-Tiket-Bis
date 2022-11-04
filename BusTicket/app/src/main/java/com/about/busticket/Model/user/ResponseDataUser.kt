package com.about.busticket.Model.user

import com.google.gson.annotations.SerializedName

data class ResponseDataUser( // sebagai respon dari user

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)
