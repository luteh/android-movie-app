package com.luteh.movieapp.data.remote.network

import com.luteh.movieapp.data.remote.response.GenreResponse
import com.luteh.movieapp.data.remote.response.discover.DiscoverResponse
import com.luteh.movieapp.data.remote.response.discover.MovieDiscoverResponse
import com.luteh.movieapp.data.remote.response.moviedetail.MovieDetailResponse
import com.luteh.movieapp.data.remote.response.moviedetail.ReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */


interface ApiService {
    companion object {
        private const val APPEND_TO_RESPONSE = "videos,reviews,images"
    }

    @GET("discover/movie")
    suspend fun getMovieDiscover(
        @Query("page") page: Int,
        @Query("with_genres") withGenres: String,
    ): DiscoverResponse?

    @GET("genre/movie/list")
    suspend fun getOfficialGenres(): GenreResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String = APPEND_TO_RESPONSE
    ): MovieDetailResponse?

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): ReviewsResponse?
}