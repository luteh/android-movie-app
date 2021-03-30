package com.luteh.main.appauth.data.local

import com.google.gson.Gson
import com.luteh.core.data.Result
import com.luteh.core.data.local.datastore.MovieDataStore
import com.luteh.main.appauth.data.local.entity.KeycloakTokenEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IamLocalDataSource @Inject constructor(private val movieDataStore: MovieDataStore) {
    suspend fun saveUserToken(keycloakTokenEntity: KeycloakTokenEntity) {
        val keycloakTokenJsonString = Gson().toJson(keycloakTokenEntity)

        Timber.d("saveUserToken: keycloakTokenJsonString-> $keycloakTokenJsonString")
        movieDataStore.saveUserToken(keycloakTokenJsonString)
    }

    fun retrieveUserToken(): Flow<Result<KeycloakTokenEntity>> = flow {
        movieDataStore.retrieveUserToken().collect {
            if (it == null) {
                emit(Result.Empty)
            } else {
                val keycloakTokenEntity = Gson().fromJson(it, KeycloakTokenEntity::class.java)
                Timber.d("retrieveUserToken: keycloakTokenEntity-> $keycloakTokenEntity")
                emit(Result.Success(keycloakTokenEntity))
            }
        }
    }

    fun isAppFirstTimeLaunched(): Flow<Result<Boolean>> = flow {
        movieDataStore.isAppFirstTimeLaunched().collect {
            emit(Result.Success(it))
        }
    }

    suspend fun changeAppState(isAppFirstTimeLaunced: Boolean) {
        movieDataStore.changeAppState(isAppFirstTimeLaunced)
    }

    fun isLoggedIn() = flow {
        movieDataStore.retrieveUserToken().collect {
            if (it == null) {
                emit(Result.Empty)
            } else {
                emit(Result.Success(it))
            }
        }
    }
}