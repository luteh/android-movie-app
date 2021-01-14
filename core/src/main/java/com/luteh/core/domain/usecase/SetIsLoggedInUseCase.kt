package com.luteh.core.domain.usecase

import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class SetIsLoggedInUseCaseParams(val value: Boolean)

class SetIsLoggedInUseCase @Inject constructor(
    private val repository: IMovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<SetIsLoggedInUseCaseParams, Unit>(dispatcher) {
    override suspend fun execute(parameters: SetIsLoggedInUseCaseParams) {
        val (value) = parameters
        repository.setIsLoggedIn(value)
    }
}