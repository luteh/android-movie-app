package com.luteh.iam.data.repository

import com.luteh.core.data.Result
import com.luteh.iam.data.remote.IamRemoteDataSource
import com.luteh.iam.domain.model.KeycloakToken
import com.luteh.iam.domain.repository.IamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IamRepositoryImpl @Inject constructor(private val iamRemoteDataSource: IamRemoteDataSource) :
    IamRepository {
    override fun obtainKeycloakToken(
        clientId: String,
        redirectUri: String,
        code: String
    ): Flow<Result<KeycloakToken>> = flow {
        emit(Result.Loading)
        when (val apiResponse =
            iamRemoteDataSource.obtainKeycloakToken(clientId, redirectUri, code).first()) {
            is Result.Success -> {
                emit(Result.Success(apiResponse.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }
}