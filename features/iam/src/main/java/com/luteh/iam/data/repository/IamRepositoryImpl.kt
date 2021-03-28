package com.luteh.iam.data.repository

import com.luteh.core.data.MovieRepository
import com.luteh.core.data.Result
import com.luteh.core.data.local.LocalDataSource
import com.luteh.core.domain.repository.IMovieRepository
import com.luteh.iam.data.local.IamLocalDataSource
import com.luteh.iam.data.remote.IamRemoteDataSource
import com.luteh.iam.domain.model.KeycloakToken
import com.luteh.iam.domain.repository.IamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IamRepositoryImpl @Inject constructor(
    private val iamRemoteDataSource: IamRemoteDataSource,
    private val iamLocalDataSource: IamLocalDataSource
) : IamRepository {
    override fun obtainKeycloakToken(
        clientId: String,
        redirectUri: String,
        code: String
    ): Flow<Result<KeycloakToken>> = flow {
        emit(Result.Loading)
        when (val apiResponse =
            iamRemoteDataSource.obtainKeycloakToken(clientId, redirectUri, code).first()) {
            is Result.Success -> {
                iamLocalDataSource.saveUserToken(apiResponse.data.toEntity())
                emit(Result.Success(apiResponse.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun retrieveUserToken(): Flow<Result<KeycloakToken>> = flow {
        emit(Result.Loading)
        when (val entity = iamLocalDataSource.retrieveUserToken().first()) {
            is Result.Success -> {
                emit(Result.Success(entity.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun isAppFirstTimeLaunched(): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        when (val isAppFirstTimeLaunched = iamLocalDataSource.isAppFirstTimeLaunched().first()) {
            is Result.Success -> {
                emit(Result.Success(isAppFirstTimeLaunched.data))
            }
        }
    }

    override suspend fun changeAppState(isAppFirstTimeLaunced: Boolean) {
        iamLocalDataSource.changeAppState(isAppFirstTimeLaunced)
    }

    override fun logOut(): Flow<Result<String>> = flow {
        emit(Result.Loading)
        when (val apiResponse = iamRemoteDataSource.logOut("", "", "").first()) {
            is Result.Success -> {
                emit(Result.Success(apiResponse.data))
            }
            is Result.Error -> {
                emit(Result.Error(apiResponse.throwable))
            }
        }
    }

    override fun isLoggedIn(): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        when(val data = iamLocalDataSource.isLoggedIn().first()){
            is Result.Success->{
                emit(Result.Success(true))
            }
            is Result.Empty->{
                emit(Result.Empty)
            }
        }
    }
}