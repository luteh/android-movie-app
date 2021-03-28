package com.luteh.iam.data.remote

import com.luteh.core.data.Result
import com.luteh.iam.data.remote.network.IamApiService
import kotlinx.coroutines.flow.flow
import okio.IOException
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IamRemoteDataSource @Inject constructor(private val iamApiService: IamApiService) {
    companion object {
        const val CLIENT_ID_ACCOUNT = "account"
        const val CLIENT_ID_ACCOUNT_CONSOLE = "account-console"
        const val CLIENT_ID_REGISTER_USER = "register-user"
        const val CLIENT_SECRET_ACCOUNT = "43f934d5-8ad3-4a9f-96fd-3f023ed73df3"
        const val CLIENT_SECRET_ACCOUNT_CONSOLE = "8fa67bec-1a48-44df-8c85-c6e68579993f"
        const val CLIENT_SECRET_REGISTER_USER = "3a087945-871d-4307-807e-1e37c13f22b6"
    }

    suspend fun obtainKeycloakToken(clientId: String, redirectUri: String, code: String) = flow {
        val clientSecret = if (clientId.equals(
                CLIENT_ID_ACCOUNT,
                true
            )
        ) CLIENT_SECRET_ACCOUNT else CLIENT_SECRET_REGISTER_USER

        val response = iamApiService.obtainKeycloackToken(
            clientId = clientId,
            clientSecret = clientSecret,
            code = code,
            redirectUri = redirectUri
        )
        if (response != null) {
            emit(Result.Success(response))
        } else {
            emit(Result.Empty)
        }
    }

    fun logOut(clientId: String, clientSecret: String, refreshToken: String) = flow {
        val response = iamApiService.logOut(clientId, clientSecret, refreshToken)
        Timber.d("logOut: response-> $response")
        when (response.code()) {
            204 -> {
                emit(Result.Success(response.message()))
            }
            else -> {
                emit(Result.Error(IOException(response.message())))
            }
        }
    }
}