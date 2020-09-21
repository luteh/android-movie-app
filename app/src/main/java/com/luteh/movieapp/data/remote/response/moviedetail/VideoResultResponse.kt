package com.luteh.movieapp.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.movieapp.domain.model.moviedetail.VideoResult

data class VideoResultResponse(
    @SerializedName("id")
    val id: String, // 5d221db394d8a87d3441b212
    @SerializedName("iso_639_1")
    val iso6391: String, // en
    @SerializedName("iso_3166_1")
    val iso31661: String, // US
    @SerializedName("key")
    val key: String, // 01ON04GCwKs
    @SerializedName("name")
    val name: String, // Disney's Mulan - Official Teaser
    @SerializedName("site")
    val site: String, // YouTube
    @SerializedName("size")
    val size: Int, // 1080
    @SerializedName("type")
    val type: String // Teaser
) {
    fun toDomain() = VideoResult(id, iso6391, iso31661, key, name, site, size, type)
}