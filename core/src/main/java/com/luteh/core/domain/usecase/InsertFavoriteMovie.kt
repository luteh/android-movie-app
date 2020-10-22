package com.luteh.core.domain.usecase

import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.model.moviedetail.MovieDetail
import com.luteh.core.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

data class InsertFavoriteMovieParams(
    val movieDetail: MovieDetail
)

class InsertFavoriteMovie @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<InsertFavoriteMovieParams, Unit>(dispatcher) {
    override suspend fun execute(parameters: InsertFavoriteMovieParams) {
        val (movieDetail) = parameters
        repository.insertFavoriteMovie(movieDetail)
    }
}