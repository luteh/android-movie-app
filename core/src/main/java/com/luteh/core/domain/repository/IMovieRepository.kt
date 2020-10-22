package com.luteh.core.domain.repository

import com.luteh.core.data.Resource
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
    fun getMovieDiscover(page: Int, withGenres: String): Flow<Resource<Discover>>
    fun getGenreList(): Flow<Resource<List<Genre>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>
    fun getReviews(movieId: Int, page: Int): Flow<Resource<Reviews>>
    //endregion

    //region Local Transaction
    suspend fun insertFavoriteMovie(movieDetail: MovieDetail)
    fun getAllFavoriteMovies():Flow<Resource<List<MovieDiscover>>>
    suspend fun deleteFavoriteMovieById(movieId: Int)
    fun getFavoriteMovieById(movieId: Int):Flow<Resource<Unit>>
    //endregion
}