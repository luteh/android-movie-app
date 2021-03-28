package com.luteh.iam.presentation.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.iam.domain.model.KeycloakToken
import com.luteh.iam.domain.usecase.RetrieveUserTokenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(private val retrieveUserTokenUseCase: RetrieveUserTokenUseCase):BaseViewModel() {

    private val _keycloakTokenLiveData = MutableLiveData<Result<KeycloakToken>>()
    val keycloakTokenLiveData : LiveData<Result<KeycloakToken>> get() = _keycloakTokenLiveData

    fun determine(){
        viewModelScope.launch {
            delay(1000)
            retrieveUserTokenUseCase(Unit).collect {
                _keycloakTokenLiveData.value = it
            }
        }
    }
}