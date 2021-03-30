package com.luteh.main.appauth.presentation.splash

import androidx.annotation.MainThread
import androidx.browser.customtabs.CustomTabsIntent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.main.appauth.domain.model.KeycloakToken
import com.luteh.main.appauth.domain.repository.IamRepository
import com.luteh.main.appauth.domain.usecase.RetrieveUserTokenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationRequest
import java.util.concurrent.atomic.AtomicReference

class SplashViewModel @ViewModelInject constructor(
    private val retrieveUserTokenUseCase: RetrieveUserTokenUseCase,
    private val repository: IamRepository
) : BaseViewModel() {

    private val _keycloakTokenLiveData = MutableLiveData<Result<KeycloakToken>>()
    val keycloakTokenLiveData: LiveData<Result<KeycloakToken>> get() = _keycloakTokenLiveData

    private val _isAuthenticatedLiveData = MutableLiveData<Boolean>()
    val isAuthenticatedLiveData: LiveData<Boolean> get() = _isAuthenticatedLiveData

    val authRequest: AtomicReference<AuthorizationRequest> get() = repository.getAuthRequest()
    val authIntent: AtomicReference<CustomTabsIntent> get() = repository.getAuthIntent()
    val authService get() = repository.getAuthService()

    fun determine() {
        viewModelScope.launch {
            delay(1000)
            retrieveUserTokenUseCase(Unit).collect {
                _keycloakTokenLiveData.value = it
            }
        }
    }

    @MainThread
    fun checkAuthenticationState() {
        _isAuthenticatedLiveData.value = repository.isAlreadyAuthenticated()
    }

    @MainThread
    fun initAppAuth(){
        repository.initAppAUth()
    }
}