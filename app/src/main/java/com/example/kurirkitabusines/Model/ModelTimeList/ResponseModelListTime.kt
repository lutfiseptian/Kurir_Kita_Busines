package com.example.kurirkitabusines.Model.ModelTimeList

import com.google.gson.annotations.SerializedName

data class ResponseModelListTime(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class City(

	@field:SerializedName("province")
	val province: Province,

	@field:SerializedName("province_id")
	val provinceId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class Group(

	@field:SerializedName("office_id")
	val officeId: Int,

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("group_id")
	val groupId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("status")
	val status: Int
)

data class Province(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class LocationItem(

	@field:SerializedName("parent")
	val parent: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("time_arrived")
	val timeArrived: String,

	@field:SerializedName("value_string")
	val valueString: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("value_numeric")
	val valueNumeric: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("key")
	val key: String,

	@field:SerializedName("status")
	val status: Int
)

data class District(

	@field:SerializedName("regency_id")
	val regencyId: String,

	@field:SerializedName("city")
	val city: City,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class Edifice(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("district")
	val district: District,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("district_id")
	val districtId: Int,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("target")
	val target: Int,

	@field:SerializedName("status")
	val status: Int
)

data class Data(

	@field:SerializedName("receipt_name")
	val receiptName: String,

	@field:SerializedName("driver_id")
	val driverId: Int,

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("edifice_id")
	val edificeId: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("group_id")
	val groupId: Int,

	@field:SerializedName("location")
	val location: List<LocationItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("edifice")
	val edifice: Edifice,

	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("group")
	val group: Group
)
