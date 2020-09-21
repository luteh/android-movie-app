package com.luteh.movieapp.data

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
sealed class Resource<out T>(val data: T? = null, val message: String? = null, val throwable: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String = "", throwable: Throwable? = null, data: T? = null) : Resource<T>(data, message, throwable)
    object Empty : Resource<Nothing>()

}