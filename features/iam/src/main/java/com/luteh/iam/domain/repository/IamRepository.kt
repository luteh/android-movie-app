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
}