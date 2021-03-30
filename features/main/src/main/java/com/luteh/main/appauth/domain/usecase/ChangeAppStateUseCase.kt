package com.luteh.main.appauth.domain.usecase

import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.usecase.UseCase
import com.luteh.main.appauth.domain.repository.IamRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class ChangeAppStateUseCaseParams(
    val isAppFirstTimeLaunced: Boolean
)

class ChangeAppStateUseCase @Inject constructor(
    private val repository: IamRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<ChangeAppStateUseCaseParams, Unit>(dispatcher) {
    override suspend fun execute(parameters: ChangeAppStateUseCaseParams) {
        val (isAppFirstTimeLaunced) = parameters
        repository.changeAppState(isAppFirstTimeLaunced)
    }
}