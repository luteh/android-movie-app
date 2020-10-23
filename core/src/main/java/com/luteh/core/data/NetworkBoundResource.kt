package com.luteh.core.data

import kotlinx.coroutines.flow.*

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Result<ResultType>> = flow {
        emit(Result.Loading)
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Result.Loading)
            when (val apiResponse = createCall().first()) {
                is Result.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Result.Success(
                            it
                        )
                    })
                }
                is Result.Empty -> {
                    emitAll(loadFromDB().map {
                        Result.Success(
                            it
                        )
                    })
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Result.Success(
                    it
                )
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<Result<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Result<ResultType>> = result
}