package com.example.kurirkitabusines.Model.ModelForgotPassword

import com.google.gson.annotations.SerializedName

data class ModelForgotPasswordResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Data(

	@field:SerializedName("param")
	val param: String? = null
)
