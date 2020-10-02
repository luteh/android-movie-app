package com.luteh.core.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.core.domain.model.moviedetail.ProductionCompany

data class ProductionCompanyResponse(
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("logo_path")
    val logoPath: String?, // /wdrCwmRnLFJhEoH8GSfymY85KHT.png
    @SerializedName("name")
    val name: String, // Walt Disney Pictures
    @SerializedName("origin_country")
    val originCountry: String // US
) {
    fun toDomain(): ProductionCompany = ProductionCompany(
        id, logoPath.orEmpty(), name, originCountry
    )
}