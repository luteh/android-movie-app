package com.luteh.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Result
import com.luteh.core.domain.model.moviedetail.MovieDetail
import com.luteh.core.domain.usecase.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class DetailViewModel @ViewModelInject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val insertFavoriteMovie: InsertFavoriteMovie,
    private val getFavoriteMovieByIdUseCase: GetFavoriteMovieByIdUseCase,
    private val deleteFavoriteMovieByIdUseCase: DeleteFavoriteMovieByIdUseCase
) : BaseViewModel() {

    private val movieIdLiveData = MutableLiveData<Int>()

    /** Helper to determine whether movie detail data has fetched or not */
    var isDataFetched = false

    var mMovieDetail: MovieDetail? = null

    private val _isFavoriteMovieLiveData = MutableLiveData<Boolean>()
    val isFavoriteMovieLiveData: LiveData<Boolean> = _isFavoriteMovieLiveData

    val movieDetailLiveData =
        movieIdLiveData.switchMap {
            launchOnViewModelScope {
                getMovieDetailUseCase(
                    GetMovieDetailUseCaseParams((it))
                ).asLiveData()
            }
        }

    fun getMovieDetail(movieId: Int) {
        movieIdLiveData.value = movieId
    }

    fun insertFavoriteMovie() {
        mMovieDetail?.let {
            viewModelScope.launch {
                insertFavoriteMovie(InsertFavoriteMovieParams(it))
                setIsFavoriteMovieLiveData(true)
            }
        }
    }

    private fun getFavoriteMovieById() {
        mMovieDetail?.let {
            viewModelScope.launch {
                getFavoriteMovieByIdUseCase(GetFavoriteMovieByIdParams(it.id)).collect {
                    setIsFavoriteMovieLiveData(it is Result.Success)
                }
            }
        }
    }

    fun deleteFavoriteMovieById() {
        mMovieDetail?.let {
            viewModelScope.launch {
                deleteFavoriteMovieByIdUseCase(DeleteFavoriteMovieByIdUseCaseParams(it.id))
                setIsFavoriteMovieLiveData(false)
            }
        }
    }

    private fun setIsFavoriteMovieLiveData(isFavoriteMovie: Boolean) {
        _isFavoriteMovieLiveData.value = isFavoriteMovie
    }

    fun onSuccessGetMovieDetail(movieDetail: MovieDetail?) {
        mMovieDetail = movieDetail
        isDataFetched = true

        getFavoriteMovieById()
    }
}