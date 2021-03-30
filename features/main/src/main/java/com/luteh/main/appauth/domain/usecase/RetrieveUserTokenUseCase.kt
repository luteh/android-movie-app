package com.luteh.main.appauth.domain.usecase

import com.luteh.core.data.Result
import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.usecase.FlowUseCase
import com.luteh.main.appauth.domain.model.KeycloakToken
import com.luteh.main.appauth.domain.repository.IamRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveUserTokenUseCase @Inject constructor(
    private val repository: IamRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, KeycloakToken>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<KeycloakToken>> {
        return repository.retrieveUserToken()
    }
}