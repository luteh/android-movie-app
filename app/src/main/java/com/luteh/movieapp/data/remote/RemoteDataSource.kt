package com.luteh.movieapp.data.remote

import com.luteh.movieapp.data.remote.network.ApiResponse
import com.luteh.movieapp.data.remote.network.ApiService
import com.luteh.movieapp.data.remote.response.GenreResponse
import com.luteh.movieapp.data.remote.response.discover.DiscoverResponse
import com.luteh.movieapp.data.remote.response.moviedetail.MovieDetailResponse
import com.luteh.movieapp.data.remote.response.moviedetail.ReviewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getMovieDiscover(page: Int, withGenres: String): Flow<ApiResponse<DiscoverResponse>> = flow {
        val response = apiService.getMovieDiscover(page, withGenres)
        if (response != null && response.results.isNotEmpty()) {
            emit(ApiResponse.Success(response))
        } else {
            emit(ApiResponse.Empty)
        }
    }

    suspend fun getOfficialGenres(): Flow<ApiResponse<List<GenreResponse.GenresResponse>>> = flow {
        val response = apiService.getOfficialGenres()
        val dataArray = response.genres
        if (dataArray.isNotEmpty()) {
            emit(ApiResponse.Success(dataArray))
        } else {
            emit(ApiResponse.Empty)
        }
    }

    suspend fun getMovieDetail(movieId: Int): Flow<ApiResponse<MovieDetailResponse>> = flow {
        val response = apiService.getMovieDetail(movieId)
        if (response != null) {
            emit(ApiResponse.Success(response))
        } else {
            emit(ApiResponse.Empty)
        }
    }

    suspend fun getReviews(movieId: Int, page: Int): Flow<ApiResponse<ReviewsResponse>> = flow {
        val response = apiService.getReviews(movieId, page)
        if (response != null && response.results.isNotEmpty()) {
            emit(ApiResponse.Success(response))
        } else {
            emit(ApiResponse.Empty)
        }
    }
}