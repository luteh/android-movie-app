package com.luteh.iam.presentation.keycloak

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.iam.data.remote.IamRemoteDataSource
import com.luteh.iam.domain.model.KeycloakToken
import com.luteh.iam.domain.usecase.ObtainKeycloakTokenUseCase
import com.luteh.iam.domain.usecase.ObtainKeycloakTokenUseCaseParams
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class KeycloakViewModel @ViewModelInject constructor(private val obtainKeycloakTokenUseCase: ObtainKeycloakTokenUseCase) :
    BaseViewModel() {

    val keycloakTokenLiveData = MutableLiveData<Result<KeycloakToken>>()

    private val _shouldShowKeycloakWebView = MutableLiveData<Boolean>()
    val shouldShowKeycloakWebView: LiveData<Boolean> get() = _shouldShowKeycloakWebView

    var clientId = ""
    private var redirectUri = ""

    fun setClientId(isUsernameExist: Boolean) {
        clientId = if (isUsernameExist) IamRemoteDataSource.CLIENT_ID_ACCOUNT else IamRemoteDataSource.CLIENT_ID_REGISTER_USER
    }

    fun setRedirectUri(redirectUri: String) {
        this.redirectUri = redirectUri
    }

    fun interceptUrl(url: Uri?) {
        Timber.d("interceptUrl() started $url")
        url?.let {
            if (it.query.isNullOrEmpty()) {
                return
            }

            Timber.d("interceptUrl: queryParameterNames-> ${it.queryParameterNames}")
            if (it.toString().contains("code=").not()) {
                return
            }

//            if (it.getBooleanQueryParameter("code", false).not()) {
//                return
//            }

//            val authorizationCode = it.getQueryParameter("code")
            val authorizationCode = it.toString().substringAfterLast("code=")
            obtainToken(authorizationCode)
        }
    }

    private fun obtainToken(authorizationCode: String?) {
        Timber.d("started $authorizationCode")
        authorizationCode?.let {
            viewModelScope.launch {
                obtainKeycloakTokenUseCase(
                    ObtainKeycloakTokenUseCaseParams(
                        clientId,
                        redirectUri,
                        authorizationCode
                    )
                ).collect {
                    keycloakTokenLiveData.value = it
                }
            }
        }
    }
}