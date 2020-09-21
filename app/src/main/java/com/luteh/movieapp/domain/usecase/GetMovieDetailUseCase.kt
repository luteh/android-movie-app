package com.luteh.movieapp.domain.usecase

import com.luteh.movieapp.data.Resource
import com.luteh.movieapp.di.IoDispatcher
import com.luteh.movieapp.domain.model.moviedetail.MovieDetail
import com.luteh.movieapp.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
data class GetMovieDetailUseCaseParams(
    val movieId: Int
)

class GetMovieDetailUseCase @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<GetMovieDetailUseCaseParams, MovieDetail>(dispatcher) {
    override fun execute(parameters: GetMovieDetailUseCaseParams): Flow<Resource<MovieDetail>> {
        val (movieId) = parameters
        return repository.getMovieDetail(movieId)
    }
}