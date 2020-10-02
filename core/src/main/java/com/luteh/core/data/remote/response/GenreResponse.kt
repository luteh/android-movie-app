package com.luteh.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.luteh.core.domain.model.moviedetail.Genre

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<GenresResponse>
) {
    data class GenresResponse(
        @SerializedName("id")
        val id: Int, // 28
        @SerializedName("name")
        val name: String // Action
    ) {
        fun toDomain() = Genre(
            id = id,
            name = name
        )
    }
}
