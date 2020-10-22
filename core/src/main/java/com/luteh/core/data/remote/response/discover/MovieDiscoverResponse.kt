package com.luteh.core.data.remote.response.discover


import com.google.gson.annotations.SerializedName
import com.luteh.core.domain.model.MovieDiscover

data class MovieDiscoverResponse(
    @SerializedName("popularity")
    val popularity: Double, // 223.791
    @SerializedName("vote_count")
    val voteCount: Int, // 772
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("poster_path")
    val posterPath: String?, // /bOKjzWDxiDkgEQznhzP4kdeAHNI.jpg
    @SerializedName("id")
    val id: Int, // 605116
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String?, // /qVygtf2vU15L2yKS4Ke44U4oMdD.jpg
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Project Power
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("title")
    val title: String, // Project Power
    @SerializedName("vote_average")
    val voteAverage: Double, // 6.7
    @SerializedName("overview")
    val overview: String, // An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers.
    @SerializedName("release_date")
    val releaseDate: String // 2020-08-14
) {
    fun toDomain()= MovieDiscover(
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        posterPath = posterPath ?: "",
        id = id,
        adult = adult,
        backdropPath = backdropPath ?: "",
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        genreIds = genreIds,
        title = title,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate
    )
}