package com.luteh.main.appauth.appauthhelper

import android.app.Activity
import android.content.Intent
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import net.openid.appauth.*
import net.openid.appauth.AuthorizationService.TokenResponseCallback
import net.openid.appauth.ClientAuthentication.UnsupportedAuthenticationMethod
import okio.buffer
import okio.source
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicReference

interface AppAuthHandlerListener {
    fun displayAuthorized()
}

class AppAuthHandler private constructor(
    private val activity: Activity,
    private val fragment:Fragment,
    private val appAuthHandlerListener: AppAuthHandlerListener?
) {
    private val KEY_USER_INFO = "userInfo"

    private var mAuthService: AuthorizationService? = null
    private var mStateManager: AuthStateManager? = null
    private val mUserInfoJson = AtomicReference<JSONObject>()
    private var mExecutor: ExecutorService? = null
    private var mConfiguration: Configuration? = null

    val mIsAuthorized get() = mStateManager!!.current.isAuthorized

    companion object {
        val END_SESSION_REQUEST_CODE = 911

        private var INSTANCE: AppAuthHandler? = null

        fun getInstance(
            activity: Activity,
            fragment: Fragment,
            appAuthHandlerListener: AppAuthHandlerListener? = null
        ): AppAuthHandler {
            if (INSTANCE == null) {
                INSTANCE =
                    AppAuthHandler(
                        activity,
                        fragment,
                        appAuthHandlerListener
                    )
            }

            return INSTANCE as AppAuthHandler
        }
    }

    fun initAppAuth() {
        Timber.d("initAppAuth: started")

        mStateManager =
            AuthStateManager.getInstance(
                activity
            )
        mExecutor = Executors.newSingleThreadExecutor()
        mConfiguration =
            Configuration.getInstance(
                activity
            )

        val config =
            Configuration.getInstance(
                activity
            )
//        if (config.hasConfigurationChanged()) {
//            Toast.makeText(
//                context,
//                "Configuration change detected",
//                Toast.LENGTH_SHORT
//            )
//                .show()
//            signOut()
//            return
//        }

        mAuthService = AuthorizationService(
            activity,
            AppAuthConfiguration.Builder()
                .setConnectionBuilder(config.connectionBuilder)
                .setSkipIssuerHttpsCheck(true)
                .setSkipNonceVerification(true)
                .build()
        )
    }

    fun onStart() {
        Timber.d("onStart: started ${mStateManager!!.current.jsonSerializeString()}")

        if (mExecutor!!.isShutdown) {
            mExecutor = Executors.newSingleThreadExecutor()
        }

        if (mStateManager!!.current.isAuthorized) {
            Timber.d("onStart: authorized")
//            displayAuthorized()
//            appAuthHandlerListener.displayAuthorized()
            return
        }

        // the stored AuthState is incomplete, so check if we are currently receiving the result of
        // the authorization flow from the browser.

        // the stored AuthState is incomplete, so check if we are currently receiving the result of
        // the authorization flow from the browser.
        val response = AuthorizationResponse.fromIntent(activity.intent)
        val ex = AuthorizationException.fromIntent(activity.intent)

        if (response != null || ex != null) {
            mStateManager!!.updateAfterAuthorization(response, ex)
        }

        when {
            response?.authorizationCode != null -> {
                // authorization code exchange is required
                mStateManager!!.updateAfterAuthorization(response, ex)
                exchangeAuthorizationCode(response)
            }
            ex != null -> {
                //            displayNotAuthorized("Authorization flow failed: " + ex.message)
            }
            else -> {
                //            displayNotAuthorized("No authorization state retained - reauthorization required")
            }
        }
    }

    @MainThread
    private fun exchangeAuthorizationCode(authorizationResponse: AuthorizationResponse) {
//        displayLoading("Exchanging authorization code")
        performTokenRequest(
            authorizationResponse.createTokenExchangeRequest(),
            TokenResponseCallback { tokenResponse: TokenResponse?, authException: AuthorizationException? ->
                this.handleCodeExchangeResponse(
                    tokenResponse,
                    authException
                )
            })
    }

    @WorkerThread
    private fun handleCodeExchangeResponse(
        tokenResponse: TokenResponse?,
        authException: AuthorizationException?
    ) {
        mStateManager!!.updateAfterTokenResponse(tokenResponse, authException)
        if (!mStateManager!!.current.isAuthorized) {
            val message = ("Authorization Code exchange failed"
                    + if (authException != null) authException.error else "")

            // WrongThread inference is incorrect for lambdas
//            runOnUiThread(Runnable { displayNotAuthorized(message) })
        } else {
//            runOnUiThread(Runnable { this.displayAuthorized() })
        }
    }

    @MainThread
    private fun performTokenRequest(
        request: TokenRequest,
        callback: TokenResponseCallback
    ) {
        val clientAuthentication: ClientAuthentication = try {
            mStateManager!!.current.clientAuthentication
        } catch (ex: UnsupportedAuthenticationMethod) {
            Timber.d("performTokenRequest: Token request cannot be made, client authentication for the token endpoint could not be constructed $ex")
//            displayNotAuthorized("Client authentication method is unsupported")
            return
        }
        mAuthService!!.performTokenRequest(
            request,
            clientAuthentication,
            callback
        )
    }

    @MainThread
    private fun refreshAccessToken() {
//        displayLoading("Refreshing access token")
        performTokenRequest(
            mStateManager!!.current.createTokenRefreshRequest()
        ) { tokenResponse: TokenResponse?, authException: AuthorizationException? ->
            this.handleAccessTokenResponse(
                tokenResponse,
                authException
            )
        }
    }

    @WorkerThread
    private fun handleAccessTokenResponse(
        tokenResponse: TokenResponse?,
        authException: AuthorizationException?
    ) {
        mStateManager!!.updateAfterTokenResponse(tokenResponse, authException)
//        runOnUiThread(Runnable { this.displayAuthorized() })
    }

    /**
     * Demonstrates the use of  {@link AuthState#} to retrieve
     * user info from the IDP's user info endpoint. This callback will negotiate a new access
     * token / id token for use in a follow-up action, or provide an error if this fails.
     */
    @MainThread
    private fun fetchUserInfo() {
//        displayLoading("Fetching user info")
        mStateManager!!.current.performActionWithFreshTokens(
            mAuthService!!
        ) { accessToken: String?, idToken: String?, ex: AuthorizationException? ->
            this.fetchUserInfo(
                accessToken!!, idToken!!, ex
            )
        }
    }

    @MainThread
    private fun fetchUserInfo(accessToken: String, idToken: String, ex: AuthorizationException?) {
        if (ex != null) {
            Timber.d("fetchUserInfo: Token refresh failed when fetching user info")
            mUserInfoJson.set(null)
//            runOnUiThread(Runnable { this.displayAuthorized() })
            return
        }
        val discovery = mStateManager!!.current
            .authorizationServiceConfiguration!!.discoveryDoc
        val userInfoEndpoint: URL = try {
            if (mConfiguration!!.userInfoEndpointUri != null) URL(
                mConfiguration!!.userInfoEndpointUri.toString()
            ) else URL(discovery!!.userinfoEndpoint.toString())
        } catch (urlEx: MalformedURLException) {
            Timber.e("fetchUserInfo: Failed to construct user info endpoint URL $urlEx")
            mUserInfoJson.set(null)
//            runOnUiThread(Runnable { this.displayAuthorized() })
            return
        }
        mExecutor!!.submit {
            try {
                val conn =
                    userInfoEndpoint.openConnection() as HttpURLConnection
                conn.setRequestProperty("Authorization", "Bearer $accessToken")
                conn.instanceFollowRedirects = false
                val response = conn.inputStream.source().buffer()
                    .readString(Charset.forName("UTF-8"))
                mUserInfoJson.set(JSONObject(response))
            } catch (ioEx: IOException) {
                Timber.e("fetchUserInfo: Network error when querying userinfo endpoint $ioEx")
//                showSnackbar("Fetching user info failed")
            } catch (jsonEx: JSONException) {
                Timber.e("Failed to parse userinfo response $jsonEx")
//                showSnackbar("Failed to parse user info")
            }
//            runOnUiThread(Runnable { this.displayAuthorized() })
        }
    }

    @MainThread
    fun endSession() {
        Timber.d("endSession: started")
        val currentState = mStateManager!!.current
        val config = currentState.authorizationServiceConfiguration
        if (config!!.endSessionEndpoint != null) {
            Timber.d("endSession: endSessionEndpoint not null")
//            mAuthService.dispose()
            val endSessionIntent = mAuthService!!.getEndSessionRequestIntent(
                EndSessionRequest.Builder(
                    config,
                    currentState.idToken!!,
                    mConfiguration!!.endSessionRedirectUri!!
                ).build()
            )
            fragment.startActivityForResult(endSessionIntent,
                END_SESSION_REQUEST_CODE
            )
            Timber.d("endSession: finished")
        } else {
            signOut()
        }
    }

    @MainThread
    fun signOut() {
        Timber.d("signOut: started")
        // discard the authorization and token state, but retain the configuration and
        // dynamic client registration (if applicable), to save from retrieving them again.
        val currentState = mStateManager!!.current
        val clearedState = AuthState(currentState.authorizationServiceConfiguration!!)
        if (currentState.lastRegistrationResponse != null) {
            clearedState.update(currentState.lastRegistrationResponse)
        }
        mStateManager!!.replace(clearedState)
        val mainIntent = Intent(activity, LoginActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(mainIntent)
        activity.finish()
    }

    fun onDestroy() {
        Timber.d("onDestroy: started")

//        mAuthService!!.dispose()
        mExecutor!!.shutdownNow()

        INSTANCE = null
    }
}