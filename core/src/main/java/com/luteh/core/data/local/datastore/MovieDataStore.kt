package com.luteh.core.data.local.datastore

import com.luteh.core.data.Result
import kotlinx.coroutines.flow.Flow

interface MovieDataStore {
    fun isLoggedIn(): Flow<Result<Boolean>>
    suspend fun setIsLoggedIn(value: Boolean)

    suspend fun saveUserToken(keycloakTokenJsonString: String)
    fun retrieveUserToken(): Flow<String?>
    fun isAppFirstTimeLaunched(): Flow<Boolean>
    suspend fun changeAppState(isAppFirstTimeLaunced: Boolean)
    suspend fun removeUserToken()
}