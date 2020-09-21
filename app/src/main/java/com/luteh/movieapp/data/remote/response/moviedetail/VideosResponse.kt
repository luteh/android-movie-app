package com.luteh.movieapp.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.movieapp.domain.model.moviedetail.Videos

data class VideosResponse(
    @SerializedName("results")
    val results: List<VideoResultResponse>
) {
    fun toDomain() = Videos(results
        .filter { it.type.equals("trailer", true) }
        .map { it.toDomain() }
    )
}