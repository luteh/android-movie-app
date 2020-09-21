package com.luteh.movieapp.domain.model.moviedetail

data class Reviews(
    val page: Int, // 1
    val reviewResults: List<ReviewResult>,
    val totalPages: Int, // 1
    val totalResults: Int // 1
)