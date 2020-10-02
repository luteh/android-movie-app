package com.luteh.core.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
data class Discover(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("total_results")
    val totalResults: Int, // 10000
    @SerializedName("total_pages")
    val totalPages: Int, // 500
    @SerializedName("results")
    val results: List<MovieDiscover>
)