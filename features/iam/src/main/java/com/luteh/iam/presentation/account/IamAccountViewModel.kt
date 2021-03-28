package com.luteh.iam.presentation.account

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.iam.domain.model.KeycloakToken
import com.luteh.iam.domain.usecase.LogOutUseCase
import com.luteh.iam.domain.usecase.RetrieveUserTokenUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class IamAccountViewModel @ViewModelInject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val retrieveUserTokenUseCase: RetrieveUserTokenUseCase
) : BaseViewModel() {

    private val _logOutProcessState = MutableLiveData<Result<String>>()
    val logOutProcessState: LiveData<Result<String>> get() = _logOutProcessState

    private val _userTokenLiveData = MutableLiveData<Result<KeycloakToken>>()
    val userTokenLiveData : LiveData<Result<KeycloakToken>> get() = _userTokenLiveData

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase(Unit).collect {
                _logOutProcessState.value = it
            }
        }
    }

    fun retrieveUserToken() {
        viewModelScope.launch {
            retrieveUserTokenUseCase(Unit).collect {
                _userTokenLiveData.value = it
            }
        }
    }
}