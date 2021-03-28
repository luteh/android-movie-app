package com.luteh.iam.domain.repository

import com.luteh.core.data.Result
import com.luteh.iam.domain.model.KeycloakToken
import kotlinx.coroutines.flow.Flow

interface IamRepository {
    fun obtainKeycloakToken(
        clientId: String,
        redirectUri: String,
        code: String
    ): Flow<Result<KeycloakToken>>

    fun retrieveUserToken(): Flow<Result<KeycloakToken>>

    fun isAppFirstTimeLaunched(): Flow<Result<Boolean>>
    suspend fun changeAppState(isAppFirstTimeLaunced: Boolean)
    fun logOut(): Flow<Result<String>>
    fun isLoggedIn(): Flow<Result<Boolean>>
}