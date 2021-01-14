package com.luteh.main.account

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.core.domain.usecase.IsLoggedInUseCase
import com.luteh.core.domain.usecase.SetIsLoggedInUseCase
import com.luteh.core.domain.usecase.SetIsLoggedInUseCaseParams
import kotlinx.coroutines.launch
import timber.log.Timber

class AccountViewModel @ViewModelInject constructor(
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val setIsLoggedInUseCase: SetIsLoggedInUseCase
) : BaseViewModel() {

    val isLoggedIn = isLoggedInUseCase.invoke(Unit).asLiveData()

    fun setIsLoggedIn(value: Boolean) {
        Timber.d("setIsLoggedIn: $value")
        viewModelScope.launch {
            setIsLoggedInUseCase(SetIsLoggedInUseCaseParams(value))
        }
    }

    fun doLogInOrOut() {
        if (isLoggedIn.value is Result.Success) {
            setIsLoggedIn((!(isLoggedIn.value as Result.Success<Boolean>).data))
        }
    }
}