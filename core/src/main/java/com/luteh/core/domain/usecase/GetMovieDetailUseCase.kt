package com.luteh.core.domain.usecase

import com.luteh.core.data.Resource
import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.model.moviedetail.MovieDetail
import com.luteh.core.domain.repository.IMovieRepository
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