package com.luteh.movieapp.data.remote.response.moviedetail


import com.google.gson.annotations.SerializedName
import com.luteh.movieapp.common.extensions.orNothing
import com.luteh.movieapp.data.remote.response.GenreResponse
import com.luteh.movieapp.domain.model.moviedetail.MovieDetail

data class MovieDetailResponse(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String?, // /zzWGRw277MNoCs3zhyG3YmYQsXv.jpg
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any?, // null
    @SerializedName("budget")
    val budget: Int, // 200000000
    @SerializedName("genres")
    val genres: List<GenreResponse.GenresResponse>,
    @SerializedName("homepage")
    val homepage: String, // https://movies.disney.com/mulan-2020
    @SerializedName("id")
    val id: Int, // 337401
    @SerializedName("imdb_id")
    val imdbId: String, // tt4566758
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Mulan
    @SerializedName("overview")
    val overview: String, // When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.
    @SerializedName("popularity")
    val popularity: Double, // 1457.551
    @SerializedName("poster_path")
    val posterPath: String?, // /aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryResponse>,
    @SerializedName("release_date")
    val releaseDate: String, // 2020-09-10
    @SerializedName("revenue")
    val revenue: Int, // 5900000
    @SerializedName("runtime")
    val runtime: Int, // 115
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>,
    @SerializedName("status")
    val status: String, // Released
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String, // Mulan
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 7.5
    @SerializedName("vote_count")
    val voteCount: Int, // 1709
    @SerializedName("videos")
    val videos: VideosResponse,
    @SerializedName("reviews")
    val reviews: ReviewsResponse,
    @SerializedName("images")
    val images: ImagesResponse
) {
    fun toDomain() = MovieDetail(
        adult = adult,
        backdropPath = backdropPath.orEmpty(),
        belongsToCollection = belongsToCollection.orNothing(),
        budget = budget,
        genres = genres.map { it.toDomain() },
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath.orEmpty(),
        productionCompanies = productionCompanies.map { it.toDomain() },
        productionCountries = productionCountries.map { it.toDomain() },
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.map { it.toDomain() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        videos = videos.toDomain(),
        reviews = reviews.toDomain(),
        images = images.toDomain()
    )
}