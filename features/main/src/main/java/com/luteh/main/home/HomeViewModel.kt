package com.luteh.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.core.domain.usecase.Category
import com.luteh.core.domain.usecase.GetMoviesByCategoryUseCase
import com.luteh.core.domain.usecase.GetMoviesByCategoryUseCaseParams
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

class HomeViewModel @ViewModelInject constructor(private val getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase) :
    BaseViewModel() {

    private val _moviesNowPlayingLiveData = MutableLiveData<Result<List<MovieDiscover>>>()
    val moviesNowPlayingLiveData: LiveData<Result<List<MovieDiscover>>> = _moviesNowPlayingLiveData

    private val _moviesPopularLiveData = MutableLiveData<Result<List<MovieDiscover>>>()
    val moviesPopularLiveData: LiveData<Result<List<MovieDiscover>>> = _moviesPopularLiveData

    private val _moviesTopRatedLiveData = MutableLiveData<Result<List<MovieDiscover>>>()
    val moviesTopRatedLiveData: LiveData<Result<List<MovieDiscover>>> = _moviesTopRatedLiveData

    fun getMoviesByNowPlaying() {
        viewModelScope.launch {
            getMoviesByCategoryUseCase(GetMoviesByCategoryUseCaseParams(Category.NOW_PLAYING)).collect {
                _moviesNowPlayingLiveData.value = it
            }
        }
    }

    fun getMoviesByPopular() {
        viewModelScope.launch {
            getMoviesByCategoryUseCase(GetMoviesByCategoryUseCaseParams(Category.POPULAR)).collect {
                _moviesPopularLiveData.value = it
            }
        }
    }

    fun getMoviesByTopRated() {
        viewModelScope.launch {
            getMoviesByCategoryUseCase(GetMoviesByCategoryUseCaseParams(Category.TOP_RATED)).collect {
                _moviesTopRatedLiveData.value = it
            }
        }
    }
}