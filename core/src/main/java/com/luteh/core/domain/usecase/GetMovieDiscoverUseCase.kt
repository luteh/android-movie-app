package com.luteh.core.domain.usecase

import com.luteh.core.data.Resource
import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.model.Discover
import com.luteh.core.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
data class GetMovieDiscoverUseCaseParams(
    val page: Int,
    val withGenres: String
)

class GetMovieDiscoverUseCase @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<GetMovieDiscoverUseCaseParams, Discover>(dispatcher) {
    override fun execute(parameters: GetMovieDiscoverUseCaseParams): Flow<Resource<Discover>> {
        val (page, withGenres) = parameters
        return repository.getMovieDiscover(page, withGenres)
    }
}