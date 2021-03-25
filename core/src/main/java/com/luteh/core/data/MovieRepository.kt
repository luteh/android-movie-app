package com.luteh.core.data

import com.luteh.core.data.local.LocalDataSource
import com.luteh.core.data.remote.RemoteDataSource
import com.luteh.core.domain.model.Discover
import com.luteh.core.domain.model.KeycloakToken
import com.luteh.core.domain.model.MovieDiscover
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
    //region Remote Transaction
    override fun obtainKeycloakToken(
        clientId: String,
        redirectUri: String,
        code: String
    ): Flow<Result<KeycloakToken>> = flow {
        emit(Result.Loading)
        when (val apiResponse =
            remoteDataSource.obtainKeycloakToken(clientId, redirectUri, code).first()) {
            is Result.Success -> {
                emit(Result.Success(apiResponse.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun getMovieDiscover(page: Int, withGenres: String): Flow<Result<Discover>> = flow {
        emit(Result.Loading)
        when (val apiResponse = remoteDataSource.getMovieDiscover(page, withGenres).first()) {
            is Result.Success -> {
                emit(Result.Success(apiResponse.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun getGenreList(): Flow<Result<List<Genre>>> = flow {
        emit(Result.Loading)
        when (val apiResponse = remoteDataSource.getOfficialGenres().first()) {
            is Result.Success -> {
                emit(Result.Success(apiResponse.data.map { it.toDomain() }))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<Result<MovieDetail>> = flow {
        emit(Result.Loading)
        when (val apiResponse = remoteDataSource.getMovieDetail(movieId).first()) {
            is Result.Success -> {
                emit(Result.Success(apiResponse.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun getReviews(movieId: Int, page: Int): Flow<Result<Reviews>> = flow {
        emit(Result.Loading)
        when (val apiResponse = remoteDataSource.getReviews(movieId, page).first()) {
            is Result.Success -> {
                emit(Result.Success(apiResponse.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun getMoviesByCategory(category: String): Flow<Result<List<MovieDiscover>>> = flow {
        emit(Result.Loading)
        val response = remoteDataSource.getMoviesByCategory(category)
        if (response.results.isNotEmpty()) {
            emit(Result.Success(response.results.map { it.toDomain() }))
        } else {
            emit(Result.Empty)
        }
    }

    //endregion

    //region Local Transaction
    override suspend fun insertFavoriteMovie(movieDetail: MovieDetail) {
        localDataSource.insertFavoriteMovie(movieDetail.toFavoriteMovieEntity())
    }

    override fun getAllFavoriteMovies(): Flow<Result<List<MovieDiscover>>> = flow {
        emit(Result.Loading)
        val data = localDataSource.getAllFavoriteMovies().first()
        if (data.isNotEmpty()) {
            emit(Result.Success(data.map { it.toMovieDiscoverDomain() }))
        } else {
            emit(Result.Empty)
        }
    }

    override suspend fun deleteFavoriteMovieById(movieId: Int) {
        localDataSource.deleteFavoriteMovieById(movieId)
    }

    override fun getFavoriteMovieById(movieId: Int): Flow<Result<Unit>> = flow {
        val data = localDataSource.getFavoriteMovieById(movieId).first()
        if (data != null) {
            emit(Result.Success(Unit))
        } else {
            emit(Result.Empty)
        }
    }

    override fun isLoggedIn(): Flow<Result<Boolean>> = localDataSource.isLoggedIn()

    override suspend fun setIsLoggedIn(value: Boolean) {
        localDataSource.setIsLoggedIn(value)
    }
    //endregion
}