package com.luteh.main.appauth.domain.usecase

import com.luteh.core.data.Result
import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.usecase.FlowUseCase
import com.luteh.main.appauth.domain.repository.IamRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: IamRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, String>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<String>> {
        return repository.logOut()
    }
}