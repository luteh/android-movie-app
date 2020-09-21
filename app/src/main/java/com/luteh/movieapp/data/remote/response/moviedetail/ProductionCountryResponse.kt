package com.luteh.movieapp.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.movieapp.domain.model.moviedetail.ProductionCountry

data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso31661: String, // CN
    @SerializedName("name")
    val name: String // China
) {
    fun toDomain() = ProductionCountry(iso31661, name)
}