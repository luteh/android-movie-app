package com.luteh.main.appauth.data.repository

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import com.luteh.core.data.Result
import com.luteh.main.appauth.data.appauth.AuthStateManager
import com.luteh.main.appauth.data.appauth.Configuration
import com.luteh.main.appauth.data.local.IamLocalDataSource
import com.luteh.main.appauth.data.remote.IamRemoteDataSource
import com.luteh.main.appauth.domain.model.KeycloakToken
import com.luteh.main.appauth.domain.repository.IamRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import net.openid.appauth.AppAuthConfiguration
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.browser.AnyBrowserMatcher
import timber.log.Timber
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IamRepositoryImpl @Inject constructor(
    private val iamRemoteDataSource: IamRemoteDataSource,
    private val iamLocalDataSource: IamLocalDataSource,
    private val configuration: Configuration,
    private val authStateManager: AuthStateManager,
    @ApplicationContext private val context: Context
) : IamRepository {

    private var mAuthService: AuthorizationService? = null

    private val mClientId = AtomicReference<String>()
    private val mAuthRequest = AtomicReference<AuthorizationRequest>()
    private val mAuthIntent = AtomicReference<CustomTabsIntent>()
    private var mAuthIntentLatch = CountDownLatch(1)

    override fun getAuthRequest(): AtomicReference<AuthorizationRequest> {
        return mAuthRequest
    }

    override fun getAuthIntent(): AtomicReference<CustomTabsIntent> {
        return mAuthIntent
    }

    override fun getAuthService(): AuthorizationService? {
        return mAuthService
    }

    override fun obtainKeycloakToken(
        clientId: String,
        redirectUri: String,
        code: String
    ): Flow<Result<KeycloakToken>> = flow {
        emit(Result.Loading)
        when (val apiResponse =
            iamRemoteDataSource.obtainKeycloakToken(clientId, redirectUri, code).first()) {
            is Result.Success -> {
                iamLocalDataSource.saveUserToken(apiResponse.data.toEntity())
                emit(Result.Success(apiResponse.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun retrieveUserToken(): Flow<Result<KeycloakToken>> = flow {
        emit(Result.Loading)
        when (val entity = iamLocalDataSource.retrieveUserToken().first()) {
            is Result.Success -> {
                emit(Result.Success(entity.data.toDomain()))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun isAppFirstTimeLaunched(): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        when (val isAppFirstTimeLaunched = iamLocalDataSource.isAppFirstTimeLaunched().first()) {
            is Result.Success -> {
                emit(Result.Success(isAppFirstTimeLaunched.data))
            }
        }
    }

    override suspend fun changeAppState(isAppFirstTimeLaunced: Boolean) {
        iamLocalDataSource.changeAppState(isAppFirstTimeLaunced)
    }

    override fun logOut(): Flow<Result<String>> = flow {
        emit(Result.Loading)
        when (val apiResponse = iamRemoteDataSource.logOut("", "", "").first()) {
            is Result.Success -> {
                emit(Result.Success(apiResponse.data))
            }
            is Result.Error -> {
                emit(Result.Error(apiResponse.throwable))
            }
        }
    }

    override fun isLoggedIn(): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        when (val data = iamLocalDataSource.isLoggedIn().first()) {
            is Result.Success -> {
                emit(Result.Success(true))
            }
            is Result.Empty -> {
                emit(Result.Empty)
            }
        }
    }

    override fun isAlreadyAuthenticated(): Boolean {
        return authStateManager.current.isAuthorized && !configuration.hasConfigurationChanged()
    }

    override fun initAppAUth() {
        Timber.d("initAppAUth: started")
        recreateAuthorizationService()

        if (authStateManager.current.authorizationServiceConfiguration != null) {
            // configuration is already created, skip to client initialization
            Timber.d("initAppAUth: auth config already established")
            initClient()
            return
        }
    }

    private fun recreateAuthorizationService() {
        if (mAuthService != null) {
            Timber.d("recreateAuthorizationService: Discarding existing AuthService instance")
            mAuthService?.dispose()
        }
        mAuthService = createAuthorizationService()
        mAuthRequest.set(null)
        mAuthIntent.set(null)
    }

    private fun initClient() {
        if (configuration.clientId != null) {
            Timber.d("initClient: Using static client ID: ${configuration.clientId}")
            // use a statically configured client ID
            mClientId.set(configuration.clientId)
            initAuthRequest()
            return
        }
    }

    private fun initAuthRequest() {
        createAuthRequest()
        warmUpBrowser()
    }

    private fun createAuthRequest() {
        val authRequestBuilder = AuthorizationRequest.Builder(
            authStateManager.current.authorizationServiceConfiguration!!,
            mClientId.get(),
            ResponseTypeValues.CODE,
            configuration.redirectUri
        ).setScope(configuration.scope)
        mAuthRequest.set(authRequestBuilder.build())
    }

    private fun warmUpBrowser() {
        mAuthIntentLatch = CountDownLatch(1)
        Timber.d("warmUpBrowser: Warming up browser instance for auth request")
        val intentBuilder =
            mAuthService!!.createCustomTabsIntentBuilder(mAuthRequest.get().toUri())
//            intentBuilder.setToolbarColor(getColorCompat(R.color.colorPrimary))
        mAuthIntent.set(intentBuilder.build())
        mAuthIntentLatch.countDown()
    }

    private fun createAuthorizationService(): AuthorizationService {
        Timber.d("createAuthorizationService: started")
        val builder = AppAuthConfiguration.Builder()
        builder.setBrowserMatcher(AnyBrowserMatcher.INSTANCE)
        builder.setConnectionBuilder(configuration.connectionBuilder)
        builder.setSkipIssuerHttpsCheck(true)
        builder.setSkipNonceVerification(true)
        return AuthorizationService(context, builder.build())
    }
}