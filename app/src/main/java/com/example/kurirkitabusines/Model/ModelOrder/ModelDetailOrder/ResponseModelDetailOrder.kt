package com.example.kurirkitabusines.Model.ModelOrder.ModelDetailOrder

import com.google.gson.annotations.SerializedName

data class ResponseModelDetailOrder(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class City(

	@field:SerializedName("province")
	val province: Province? = null,

	@field:SerializedName("province_id")
	val provinceId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Group(

	@field:SerializedName("office_id")
	val officeId: Int? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("group_id")
	val groupId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Province(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class LocationItem(

	@field:SerializedName("parent")
	val parent: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("time_arrived")
	val timeArrived: String? = null,

	@field:SerializedName("value_string")
	val valueString: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("value_numeric")
	val valueNumeric: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class District(

	@field:SerializedName("regency_id")
	val regencyId: String? = null,

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Edifice(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("district")
	val district: District? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("district_id")
	val districtId: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("target")
	val target: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Data(

	@field:SerializedName("receipt_name")
	val receiptName: String? = null,

	@field:SerializedName("driver_id")
	val driverId: Int? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("edifice_id")
	val edificeId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("group_id")
	val groupId: Int? = null,

	@field:SerializedName("location")
	val location: List<LocationItem>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("edifice")
	val edifice: Edifice? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("group")
	val group: Group? = null
)
