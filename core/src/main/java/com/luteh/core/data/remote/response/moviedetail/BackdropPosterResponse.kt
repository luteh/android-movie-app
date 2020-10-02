package com.luteh.core.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.core.domain.model.moviedetail.BackdropPoster

data class BackdropPosterResponse(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double, // 0.6666666666666666
    @SerializedName("file_path")
    val filePath: String, // /aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg
    @SerializedName("height")
    val height: Int, // 3000
    @SerializedName("iso_639_1")
    val iso6391: String?, // en
    @SerializedName("vote_average")
    val voteAverage: Double, // 5.9
    @SerializedName("vote_count")
    val voteCount: Int, // 19
    @SerializedName("width")
    val width: Int // 2000
) {
    fun toDomain(): BackdropPoster = BackdropPoster(
        aspectRatio,
        filePath,
        height,
        iso6391.orEmpty(),
        voteAverage,
        voteCount,
        width
    )
}