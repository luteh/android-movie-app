package com.luteh.core.data.local.datastore

import com.luteh.core.data.Result
import kotlinx.coroutines.flow.Flow

interface MovieDataStore {
    fun isLoggedIn(): Flow<Result<Boolean>>
    suspend fun setIsLoggedIn(value: Boolean)
}