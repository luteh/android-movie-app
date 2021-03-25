package com.luteh.core.data.remote.network

import com.luteh.core.data.remote.response.GenreResponse
import com.luteh.core.data.remote.response.KeycloackTokenResponse
import com.luteh.core.data.remote.response.discover.DiscoverResponse
import com.luteh.core.data.remote.response.moviedetail.MovieDetailResponse
import com.luteh.core.data.remote.response.moviedetail.ReviewsResponse
import retrofit2.http.*

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

    @GET("movie/{category}")
    suspend fun getMoviesByCategory(
        @Path("category") category: String
    ): DiscoverResponse

    @POST("realms/myrealm/protocol/openid-connect/token")
    @FormUrlEncoded
    suspend fun obtainKeycloackToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): KeycloackTokenResponse?
}