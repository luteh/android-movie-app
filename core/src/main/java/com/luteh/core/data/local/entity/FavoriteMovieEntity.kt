package com.luteh.core.data.local.entity


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luteh.core.domain.model.MovieDiscover

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    val id: Int, // 605116
    @ColumnInfo(name = "poster_path")
    val posterPath: String, // /TnOeov4w0sTtV2gqICqIxVi74V.jpg
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String, // /qVygtf2vU15L2yKS4Ke44U4oMdD.jpg
    @ColumnInfo(name = "original_title")
    val originalTitle: String, // Project Powe
    @ColumnInfo(name = "title")
    val title: String, // Project Power
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double, // 6.7
    @ColumnInfo(name = "overview")
    val overview: String, // An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers.
    @ColumnInfo(name = "release_date")
    val releaseDate: String // 2020-08-14
) {
    fun toMovieDiscoverDomain() = MovieDiscover(
        popularity = 0.0,
        voteCount = 0,
        video = false,
        posterPath = posterPath,
        id = id,
        adult = false,
        backdropPath = backdropPath,
        originalLanguage = "",
        originalTitle = originalTitle,
        genreIds = emptyList(),
        title = title,
        voteAverage = voteAverage,
        overview = overview,
        releaseDate = releaseDate
    )
}