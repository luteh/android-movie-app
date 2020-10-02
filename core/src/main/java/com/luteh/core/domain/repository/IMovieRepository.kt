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
    fun getMovieDiscover(page: Int, withGenres: String): Flow<Resource<Discover>>
    fun getGenreList(): Flow<Resource<List<Genre>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>
    fun getReviews(movieId: Int, page: Int): Flow<Resource<Reviews>>
}