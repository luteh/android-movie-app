package com.luteh.core.domain.usecase

import com.luteh.core.data.Resource
import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.model.moviedetail.Genre
import com.luteh.core.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class GetGenresUseCase @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<Genre>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Resource<List<Genre>>> {
        return repository.getGenreList()
    }
}