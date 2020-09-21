package com.luteh.movieapp.domain.model.moviedetail

data class MovieDetail(
    val adult: Boolean, // false
    val backdropPath: String, // /zzWGRw277MNoCs3zhyG3YmYQsXv.jpg
    val belongsToCollection: Any, // null
    val budget: Int, // 200000000
    val genres: List<Genre>,
    val homepage: String, // https://movies.disney.com/mulan-2020
    val id: Int, // 337401
    val imdbId: String, // tt4566758
    val originalLanguage: String, // en
    val originalTitle: String, // Mulan
    val overview: String, // When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.
    val popularity: Double, // 1457.551
    val posterPath: String, // /aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String, // 2020-09-10
    val revenue: Int, // 5900000
    val runtime: Int, // 115
    val spokenLanguages: List<SpokenLanguage>,
    val status: String, // Released
    val tagline: String,
    val title: String, // Mulan
    val video: Boolean, // false
    val voteAverage: Double, // 7.5
    val voteCount: Int, // 1709
    val videos: Videos,
    val reviews: Reviews,
    val images: Images
)