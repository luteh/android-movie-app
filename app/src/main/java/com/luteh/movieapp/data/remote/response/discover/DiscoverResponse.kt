package com.luteh.movieapp.data.remote.response.discover


import com.google.gson.annotations.SerializedName
import com.luteh.movieapp.domain.model.Discover

data class DiscoverResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("total_results")
    val totalResults: Int, // 10000
    @SerializedName("total_pages")
    val totalPages: Int, // 500
    @SerializedName("results")
    val results: List<MovieDiscoverResponse>
) {
    fun toDomain() = Discover(
        page = page,
        totalResults = totalResults,
        totalPages = totalPages,
        results = results.map { it.toDomain() }
    )
}