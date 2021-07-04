package com.example.kurirkitabusines.Model.ModelOrderPage

import com.example.kurirkitabusines.Model.ModelOrder.ModelListOrder.DataItem
import com.google.gson.annotations.SerializedName

data class DataPage(

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