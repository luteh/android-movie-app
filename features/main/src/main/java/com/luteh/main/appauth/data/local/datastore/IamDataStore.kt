package com.luteh.main.appauth.data.local.datastore

import com.luteh.main.appauth.data.local.entity.KeycloakTokenEntity
import kotlinx.coroutines.flow.Flow

interface IamDataStore {
    suspend fun saveUserToken(keycloakTokenEntity: KeycloakTokenEntity)
    fun retrieveUserToken(): Flow<KeycloakTokenEntity?>
    fun isAppFirstTimeLaunched(): Flow<Boolean>
    suspend fun changeAppState(isAppFirstTimeLaunced: Boolean)
    suspend fun removeUserToken()
}