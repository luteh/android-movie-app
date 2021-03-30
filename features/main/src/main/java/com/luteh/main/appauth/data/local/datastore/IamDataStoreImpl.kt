package com.luteh.main.appauth.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import com.luteh.main.appauth.data.local.datastore.DataStoreKeys.KEY_IS_APP_FIRST_TIME_LAUNCHED
import com.luteh.main.appauth.data.local.datastore.DataStoreKeys.KEY_USER_TOKEN
import com.luteh.main.appauth.data.local.entity.KeycloakTokenEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class IamDataStoreImpl @Inject constructor(@ApplicationContext context: Context) :
    IamDataStore {

    private val dataStore = context.createDataStore(name = "iam_data_store")

    override suspend fun saveUserToken(keycloakTokenEntity: KeycloakTokenEntity) {
        Timber.d("saveUserToken: $keycloakTokenEntity")
        Timber.d("saveUserToken: dataStore-> $dataStore")
        dataStore.edit {
            it[KEY_IS_APP_FIRST_TIME_LAUNCHED] = true
        }
    }

    override fun retrieveUserToken(): Flow<KeycloakTokenEntity?> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[KEY_USER_TOKEN] }

    override fun isAppFirstTimeLaunched(): Flow<Boolean> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[KEY_IS_APP_FIRST_TIME_LAUNCHED] ?: true }

    override suspend fun changeAppState(isAppFirstTimeLaunced: Boolean) {
        dataStore.edit {
            it[KEY_IS_APP_FIRST_TIME_LAUNCHED] = isAppFirstTimeLaunced
        }
    }

    override suspend fun removeUserToken() {
        dataStore.edit {
            it.remove(KEY_USER_TOKEN)
        }
    }
}

private object DataStoreKeys {
    val KEY_USER_TOKEN = preferencesKey<KeycloakTokenEntity>("KEY_USER_TOKEN")
    val KEY_IS_APP_FIRST_TIME_LAUNCHED = preferencesKey<Boolean>("KEY_IS_APP_FIRST_TIME_LAUNCHED")
}