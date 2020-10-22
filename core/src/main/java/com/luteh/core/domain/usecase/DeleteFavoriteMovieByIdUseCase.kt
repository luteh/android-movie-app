package com.luteh.core.domain.usecase

import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

data class DeleteFavoriteMovieByIdUseCaseParams(
    val movieId: Int
)

class DeleteFavoriteMovieByIdUseCase @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<DeleteFavoriteMovieByIdUseCaseParams, Unit>(dispatcher) {
    override suspend fun execute(parameters: DeleteFavoriteMovieByIdUseCaseParams) {
        val (movieId) = parameters
        repository.deleteFavoriteMovieById(movieId)
    }
}