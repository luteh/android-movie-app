package com.luteh.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDiscover(
    val popularity: Double, // 223.791
    val voteCount: Int, // 772
    val video: Boolean, // false
    val posterPath: String, // /bOKjzWDxiDkgEQznhzP4kdeAHNI.jpg
    val id: Int, // 605116
    val adult: Boolean, // false
    val backdropPath: String, // /qVygtf2vU15L2yKS4Ke44U4oMdD.jpg
    val originalLanguage: String, // en
    val originalTitle: String, // Project Power
    val genreIds: List<Int>,
    val title: String, // Project Power
    val voteAverage: Double, // 6.7
    val overview: String, // An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers.
    val releaseDate: String // 2020-08-14
) : Parcelable