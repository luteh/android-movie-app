package com.luteh.movieapp.domain.repository

import com.luteh.movieapp.data.Resource
import com.luteh.movieapp.domain.model.Discover
import com.luteh.movieapp.domain.model.MovieDiscover
import com.luteh.movieapp.domain.model.moviedetail.Genre
import com.luteh.movieapp.domain.model.moviedetail.MovieDetail
import com.luteh.movieapp.domain.model.moviedetail.Reviews
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