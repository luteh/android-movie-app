package com.luteh.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.domain.usecase.GetMovieDetailUseCase
import com.luteh.core.domain.usecase.GetMovieDetailUseCaseParams

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class DetailViewModel @ViewModelInject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase) : BaseViewModel() {

    private val movieIdLiveData = MutableLiveData<Int>()
    var isDataFetched = false

    val movieDetailLiveData =
        movieIdLiveData.switchMap { launchOnViewModelScope { getMovieDetailUseCase(GetMovieDetailUseCaseParams((it))).asLiveData() } }

    fun getMovieDetail(movieId: Int) {
        movieIdLiveData.value = movieId
    }
}