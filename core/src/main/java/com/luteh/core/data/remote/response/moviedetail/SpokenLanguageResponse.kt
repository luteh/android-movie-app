package com.luteh.core.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.core.domain.model.moviedetail.SpokenLanguage

data class SpokenLanguageResponse(
    @SerializedName("iso_639_1")
    val iso6391: String, // en
    @SerializedName("name")
    val name: String // English
) {
    fun toDomain() = SpokenLanguage(iso6391, name)
}