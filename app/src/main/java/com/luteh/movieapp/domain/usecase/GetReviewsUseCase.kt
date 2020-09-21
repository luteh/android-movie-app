package com.luteh.movieapp.domain.usecase

import com.luteh.movieapp.data.Resource
import com.luteh.movieapp.di.IoDispatcher
import com.luteh.movieapp.domain.model.moviedetail.Reviews
import com.luteh.movieapp.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
data class GetReviewsUseCaseParams(
    val movieId: Int,
    val page: Int
)

class GetReviewsUseCase @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<GetReviewsUseCaseParams, Reviews>(dispatcher) {
    override fun execute(parameters: GetReviewsUseCaseParams): Flow<Resource<Reviews>> {
        val (movieId, page) = parameters
        return repository.getReviews(movieId, page)
    }
}