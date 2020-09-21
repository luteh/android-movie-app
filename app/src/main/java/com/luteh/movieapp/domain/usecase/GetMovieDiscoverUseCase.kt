package com.luteh.movieapp.domain.usecase

import com.luteh.movieapp.data.Resource
import com.luteh.movieapp.di.IoDispatcher
import com.luteh.movieapp.domain.model.Discover
import com.luteh.movieapp.domain.repository.IMovieRepository
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