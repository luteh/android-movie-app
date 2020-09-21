package com.luteh.movieapp.ui.discover

import androidx.annotation.MainThread
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.luteh.movieapp.common.base.BaseViewModel
import com.luteh.movieapp.data.Resource
import com.luteh.movieapp.domain.model.Discover
import com.luteh.movieapp.domain.usecase.GetMovieDiscoverUseCase
import com.luteh.movieapp.domain.usecase.GetMovieDiscoverUseCaseParams

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
    val discoverMovies: LiveData<Resource<Discover>>

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
