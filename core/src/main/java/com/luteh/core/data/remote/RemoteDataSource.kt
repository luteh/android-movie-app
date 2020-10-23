package com.luteh.core.data.remote

import com.luteh.core.data.Result
import com.luteh.core.data.remote.network.ApiService
import com.luteh.core.data.remote.response.GenreResponse
import com.luteh.core.data.remote.response.discover.DiscoverResponse
import com.luteh.core.data.remote.response.moviedetail.MovieDetailResponse
import com.luteh.core.data.remote.response.moviedetail.ReviewsResponse
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
    suspend fun getMovieDiscover(page: Int, withGenres: String): Flow<Result<DiscoverResponse>> =
        flow {
            val response = apiService.getMovieDiscover(page, withGenres)
            if (response != null && response.results.isNotEmpty()) {
                emit(Result.Success(response))
            } else {
                emit(Result.Empty)
            }
        }

    suspend fun getOfficialGenres(): Flow<Result<List<GenreResponse.GenresResponse>>> = flow {
        val response = apiService.getOfficialGenres()
        val dataArray = response.genres
        if (dataArray.isNotEmpty()) {
            emit(Result.Success(dataArray))
        } else {
            emit(Result.Empty)
        }
    }

    suspend fun getMovieDetail(movieId: Int): Flow<Result<MovieDetailResponse>> = flow {
        val response = apiService.getMovieDetail(movieId)
        if (response != null) {
            emit(Result.Success(response))
        } else {
            emit(Result.Empty)
        }
    }

    suspend fun getReviews(movieId: Int, page: Int): Flow<Result<ReviewsResponse>> = flow {
        val response = apiService.getReviews(movieId, page)
        if (response != null && response.results.isNotEmpty()) {
            emit(Result.Success(response))
        } else {
            emit(Result.Empty)
        }
    }

    suspend fun getMoviesByCategory(category: String) = apiService.getMoviesByCategory(category)
}