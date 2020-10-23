package com.luteh.core.domain.usecase

import com.luteh.core.data.Result
import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

data class GetFavoriteMovieByIdParams(
    val movieId: Int
)

class GetFavoriteMovieByIdUseCase @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<GetFavoriteMovieByIdParams, Unit>(dispatcher){
    override fun execute(parameters: GetFavoriteMovieByIdParams): Flow<Result<Unit>> {
        val (movieId) = parameters
        return repository.getFavoriteMovieById(movieId)
    }
}