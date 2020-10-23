package com.luteh.core.domain.usecase

import com.luteh.core.data.Result
import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.core.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

enum class Category(val categoryName: String) {
    NOW_PLAYING("now_playing"), POPULAR("popular"), TOP_RATED("top_rated"), UPCOMING("upcoming")
}

data class GetMoviesByCategoryUseCaseParams(val category: Category)

class GetMoviesByCategoryUseCase @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<GetMoviesByCategoryUseCaseParams, List<MovieDiscover>>(dispatcher) {
    override fun execute(parameters: GetMoviesByCategoryUseCaseParams): Flow<Result<List<MovieDiscover>>> {
        val (category) = parameters
        return repository.getMoviesByCategory(category.categoryName)
    }
}