package com.luteh.core.data.remote

import com.luteh.core.data.Result
import com.luteh.core.data.di.NetworkModule
import com.luteh.core.data.remote.network.ApiService
import com.luteh.core.data.remote.response.GenreResponse
import com.luteh.core.data.remote.response.discover.DiscoverResponse
import com.luteh.core.data.remote.response.moviedetail.MovieDetailResponse
import com.luteh.core.data.remote.response.moviedetail.ReviewsResponse
import com.luteh.core.di.KeycloakApiService
import com.luteh.core.di.MovieApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@Singleton
class RemoteDataSource @Inject constructor(
    @MovieApiService private val apiService: ApiService,
    @KeycloakApiService private val keycloakApiService: ApiService
) {
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

    suspend fun obtainKeycloakToken(clientId: String, redirectUri: String, code: String) = flow {
        val clientSecret = if (clientId.equals(
                "account",
                true
            )
        ) "43f934d5-8ad3-4a9f-96fd-3f023ed73df3" else "3a087945-871d-4307-807e-1e37c13f22b6"

        val response = keycloakApiService.obtainKeycloackToken(
            clientId = clientId,
            clientSecret = clientSecret,
            code = code,
            redirectUri = redirectUri
        )
        if (response != null) {
            emit(Result.Success(response))
        } else {
            emit(Result.Empty)
        }
    }
}