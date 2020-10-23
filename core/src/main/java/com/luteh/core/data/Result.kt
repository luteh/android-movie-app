package com.luteh.core.data

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object Empty : Result<Nothing>()

}