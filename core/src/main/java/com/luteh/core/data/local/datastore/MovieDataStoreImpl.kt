package com.luteh.core.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.core.remove
import androidx.datastore.preferences.createDataStore
import com.luteh.core.data.Result
import com.luteh.core.data.local.datastore.DataStoreKeys.KEY_IS_APP_FIRST_TIME_LAUNCHED
import com.luteh.core.data.local.datastore.DataStoreKeys.KEY_USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

private const val DATA_STORE_NAME = "movie_data_store"

class MovieDataStoreImpl @Inject constructor(
    @ApplicationContext context: Context
) : MovieDataStore {

    private val dataStore = context.createDataStore(
        name = DATA_STORE_NAME,
    )

    override fun isLoggedIn(): Flow<Result<Boolean>> = dataStore.data.catch { exception ->
        // dataStore.data throws an IOException when an error is encountered when reading data
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { Result.Success(it[DataStoreKeys.KEY_IS_LOGGED_IN] ?: false) }

    override suspend fun setIsLoggedIn(value: Boolean) {
        dataStore.edit {
            it[DataStoreKeys.KEY_IS_LOGGED_IN] = value
        }
    }

    override suspend fun saveUserToken(keycloakTokenJsonString: String) {
        Timber.d("saveUserToken: dataStore-> $dataStore")
        dataStore.edit {
            it[KEY_USER_TOKEN] = keycloakTokenJsonString
        }
    }

    override fun retrieveUserToken(): Flow<String?> =
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
    val KEY_IS_LOGGED_IN = preferencesKey<Boolean>("KEY_IS_LOGGED_IN")

    val KEY_IS_APP_FIRST_TIME_LAUNCHED = preferencesKey<Boolean>("KEY_IS_APP_FIRST_TIME_LAUNCHED")
    val KEY_USER_TOKEN = preferencesKey<String>("KEY_USER_TOKEN")

}