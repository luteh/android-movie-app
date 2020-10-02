package com.luteh.core.data.local

import com.luteh.core.data.local.entity.MovieDiscoverEntity
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
    fun getAllMovie(): Flow<List<MovieDiscoverEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieDiscoverEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieDiscoverEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieDiscoverEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}