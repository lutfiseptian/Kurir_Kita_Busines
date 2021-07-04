package com.example.kurirkitabusines.Model.ModelOrder.ModelListOrder

import com.google.gson.annotations.SerializedName

data class ResponseModelListOrder(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class District(

	@field:SerializedName("kabkot_id")
	val kabkotId: Int? = null,

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("kecamatan")
	val kecamatan: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class DataItem(

	@field:SerializedName("edifice_id")
	val edificeId: Int? = null,

	@field:SerializedName("driver_id")
	val driverId: Int? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("group_id")
	val groupId: Int? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("edifice")
	val edifice: Edifice? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("group")
	val group: Group? = null
)

data class Subdistrict(

	@field:SerializedName("district")
	val district: District? = null,

	@field:SerializedName("kd_pos")
	val kdPos: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("kecamatan_id")
	val kecamatanId: Int? = null,

	@field:SerializedName("kelurahan")
	val kelurahan: String? = null
)

data class Edifice(

	@field:SerializedName("subdistrict_id")
	val subdistrictId: Int? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("category_id")
	val categoryId: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("subdistrict")
	val subdistrict: Subdistrict? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("postal_code")
	val postalCode: String? = null,

	@field:SerializedName("target")
	val target: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Data(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class Province(

	@field:SerializedName("provinsi")
	val provinsi: String? = null,

	@field:SerializedName("ibukota")
	val ibukota: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("p_bsni")
	val pBsni: String? = null
)

data class Group(

	@field:SerializedName("office_id")
	val officeId: Int? = null,

	@field:SerializedName("driver_id")
	val driverId: Int? = null,

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

data class City(

	@field:SerializedName("ibukota")
	val ibukota: String? = null,

	@field:SerializedName("kabupaten_kota")
	val kabupatenKota: String? = null,

	@field:SerializedName("provinsi_id")
	val provinsiId: Int? = null,

	@field:SerializedName("province")
	val province: Province? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("k_bsni")
	val kBsni: String? = null
)
