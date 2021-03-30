package com.luteh.main.appauth.domain.usecase

import com.luteh.core.data.Result
import com.luteh.core.di.IoDispatcher
import com.luteh.core.domain.usecase.FlowUseCase
import com.luteh.main.appauth.domain.model.KeycloakToken
import com.luteh.main.appauth.domain.repository.IamRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class ObtainKeycloakTokenUseCaseParams(
    val clientId: String,
    val redirectUri: String,
    val code: String
)

class ObtainKeycloakTokenUseCase @Inject constructor(
    private val repository: IamRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<ObtainKeycloakTokenUseCaseParams, KeycloakToken>(dispatcher) {
    override fun execute(parameters: ObtainKeycloakTokenUseCaseParams): Flow<Result<KeycloakToken>> {
        val (clientId, redirectUri, code) = parameters
        return repository.obtainKeycloakToken(clientId, redirectUri, code)
    }
}