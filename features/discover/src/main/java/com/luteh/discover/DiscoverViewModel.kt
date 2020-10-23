package com.luteh.discover

import androidx.annotation.MainThread
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.core.domain.model.Discover
import com.luteh.core.domain.usecase.GetMovieDiscoverUseCase
import com.luteh.core.domain.usecase.GetMovieDiscoverUseCaseParams

/**
 * Created by Luthfan Maftuh on 8/26/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class DiscoverViewModel @ViewModelInject constructor(private val getMovieDiscoverUseCase: GetMovieDiscoverUseCase) :
    BaseViewModel() {

    var currentPage = 1
    var withGenres = ""
    var isLastItemMovies = false

    private val getMoviePage = MutableLiveData<Int>()
    val discoverMovies: LiveData<Result<Discover>>

    init {
        discoverMovies = getMoviePage.switchMap {
            launchOnViewModelScope { getMovieDiscoverUseCase(GetMovieDiscoverUseCaseParams(it, withGenres)).asLiveData() }
        }
    }

    @MainThread
    fun getMovies() {
        getMoviePage.value = currentPage
        currentPage++
    }
}
