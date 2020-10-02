package com.luteh.core.data.local.entity


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_discover")
data class MovieDiscoverEntity(
    @ColumnInfo(name = "popularity")
    val popularity: Double, // 614.082
    @ColumnInfo(name = "vote_count")
    val voteCount: Int, // 869
    @ColumnInfo(name = "video")
    val video: Boolean, // false
    @ColumnInfo(name = "poster_path")
    val posterPath: String, // /TnOeov4w0sTtV2gqICqIxVi74V.jpg
    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    val id: Int, // 605116
    @ColumnInfo(name = "adult")
    val adult: Boolean, // false
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String, // /qVygtf2vU15L2yKS4Ke44U4oMdD.jpg
    @ColumnInfo(name = "original_language")
    val originalLanguage: String, // en
    @ColumnInfo(name = "original_title")
    val originalTitle: String, // Project Power
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>,
    @ColumnInfo(name = "title")
    val title: String, // Project Power
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double, // 6.7
    @ColumnInfo(name = "overview")
    val overview: String, // An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers.
    @ColumnInfo(name = "release_date")
    val releaseDate: String, // 2020-08-14
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)