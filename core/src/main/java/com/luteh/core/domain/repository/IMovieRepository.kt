package com.luteh.core.domain.repository

import com.luteh.core.data.Result
import com.luteh.core.domain.model.Discover
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.core.domain.model.moviedetail.Genre
import com.luteh.core.domain.model.moviedetail.MovieDetail
import com.luteh.core.domain.model.moviedetail.Reviews
import kotlinx.coroutines.flow.Flow

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
interface IMovieRepository {
    //region Remote Transaction
    fun getMovieDiscover(page: Int, withGenres: String): Flow<Result<Discover>>
    fun getGenreList(): Flow<Result<List<Genre>>>
    fun getMovieDetail(movieId: Int): Flow<Result<MovieDetail>>
    fun getReviews(movieId: Int, page: Int): Flow<Result<Reviews>>
    fun getMoviesByCategory(category: String): Flow<Result<List<MovieDiscover>>>
    //endregion

    //region Local Transaction
    suspend fun insertFavoriteMovie(movieDetail: MovieDetail)
    fun getAllFavoriteMovies(): Flow<Result<List<MovieDiscover>>>
    suspend fun deleteFavoriteMovieById(movieId: Int)
    fun getFavoriteMovieById(movieId: Int): Flow<Result<Unit>>

    fun isLoggedIn(): Flow<Result<Boolean>>
    suspend fun setIsLoggedIn(value: Boolean)
    //endregion
}