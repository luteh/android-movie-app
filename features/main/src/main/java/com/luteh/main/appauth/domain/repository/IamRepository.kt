package com.luteh.main.appauth.domain.repository

import androidx.browser.customtabs.CustomTabsIntent
import com.luteh.core.data.Result
import com.luteh.main.appauth.domain.model.KeycloakToken
import kotlinx.coroutines.flow.Flow
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import java.util.concurrent.atomic.AtomicReference

interface IamRepository {
    fun obtainKeycloakToken(
        clientId: String,
        redirectUri: String,
        code: String
    ): Flow<Result<KeycloakToken>>

    fun retrieveUserToken(): Flow<Result<KeycloakToken>>

    fun isAppFirstTimeLaunched(): Flow<Result<Boolean>>
    suspend fun changeAppState(isAppFirstTimeLaunced: Boolean)
    fun logOut(): Flow<Result<String>>
    fun isLoggedIn(): Flow<Result<Boolean>>

    fun isAlreadyAuthenticated(): Boolean
    fun initAppAUth()

    fun getAuthRequest() : AtomicReference<AuthorizationRequest>
    fun getAuthIntent() : AtomicReference<CustomTabsIntent>
    fun getAuthService() : AuthorizationService?
}