package com.luteh.core.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.luteh.core.data.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
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
}

private object DataStoreKeys {
    val KEY_IS_LOGGED_IN = preferencesKey<Boolean>("KEY_IS_LOGGED_IN")
}