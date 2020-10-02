package com.luteh.core.data

import com.luteh.core.data.local.LocalDataSource
import com.luteh.core.data.remote.RemoteDataSource
import com.luteh.core.data.remote.network.ApiResponse
import com.luteh.core.domain.model.Discover
import com.luteh.core.domain.model.moviedetail.Genre
import com.luteh.core.domain.model.moviedetail.MovieDetail
import com.luteh.core.domain.model.moviedetail.Reviews
import com.luteh.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : IMovieRepository {
    override fun getMovieDiscover(page: Int, withGenres: String): Flow<Resource<Discover>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getMovieDiscover(page, withGenres).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data.toDomain()))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty)
            }
        }
    }

    override fun getGenreList(): Flow<Resource<List<Genre>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getOfficialGenres().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data.map { it.toDomain() }))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Success<List<Genre>>(emptyList()))
            }
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getMovieDetail(movieId).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data.toDomain()))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty)
            }
        }
    }

    override fun getReviews(movieId: Int, page: Int): Flow<Resource<Reviews>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getReviews(movieId, page).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data.toDomain()))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty)
            }
        }
    }
}