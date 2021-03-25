package com.luteh.iam.presentation.keycloak

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.iam.domain.model.KeycloakToken
import com.luteh.iam.domain.usecase.ObtainKeycloakTokenUseCase
import com.luteh.iam.domain.usecase.ObtainKeycloakTokenUseCaseParams
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class KeycloakViewModel @ViewModelInject constructor(private val obtainKeycloakTokenUseCase: ObtainKeycloakTokenUseCase) :
    BaseViewModel() {

    val keycloakTokenLiveData = MutableLiveData<Result<KeycloakToken>>()

    private var clientId = ""
    private var redirectUri = ""

    fun setClientId(isUsernameExist: Boolean) {
        clientId = if (isUsernameExist) "account" else "register-user"
    }

    fun setRedirectUri(redirectUri:String){
        this.redirectUri = redirectUri
    }

    fun interceptUrl(url: Uri?) {
        Timber.d("interceptUrl() started $url")
        url?.let {
            if (it.query.isNullOrEmpty()) {
                return
            }

            if (it.getBooleanQueryParameter("code", false).not()) {
                return
            }

            val authorizationCode = it.getQueryParameter("code")
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