package com.luteh.core.data.local

import com.luteh.core.data.local.entity.FavoriteMovieEntity
import com.luteh.core.data.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>> = movieDao.getAllFavoriteMovies()

    suspend fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) =
        movieDao.insertFavoriteMovie(favoriteMovieEntity)

    fun updateFavoriteMovie(favoriteMovie: FavoriteMovieEntity) {
        movieDao.updateFavoriteMovie(favoriteMovie)
    }

    suspend fun deleteFavoriteMovieById(movieId: Int) =
        movieDao.deleteFavoriteMovieById(movieId)

    fun getFavoriteMovieById(movieId: Int) = movieDao.getFavoriteMovieById(movieId)
}