package com.luteh.movieapp.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.movieapp.domain.model.moviedetail.Reviews

data class ReviewsResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<ReviewResultResponse>,
    @SerializedName("total_pages")
    val totalPages: Int, // 1
    @SerializedName("total_results")
    val totalResults: Int // 1
) {
    fun toDomain() = Reviews(page, results.map { it.toDomain() }, totalPages, totalResults)
}