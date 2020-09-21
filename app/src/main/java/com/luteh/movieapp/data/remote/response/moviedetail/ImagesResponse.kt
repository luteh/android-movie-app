package com.luteh.movieapp.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.movieapp.domain.model.moviedetail.Images

data class ImagesResponse(
    @SerializedName("backdrops")
    val backdrops: List<BackdropPosterResponse>,
    @SerializedName("posters")
    val posters: List<BackdropPosterResponse>
) {
    fun toDomain(): Images = Images(
        backdrops = backdrops.map { it.toDomain() },
        posters = posters.map { it.toDomain() }
    )
}