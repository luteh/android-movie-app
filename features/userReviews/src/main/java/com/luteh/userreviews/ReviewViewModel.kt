package com.luteh.userreviews

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.luteh.core.common.base.BaseViewModel
import com.luteh.core.data.Resource
import com.luteh.core.domain.model.moviedetail.Reviews
import com.luteh.core.domain.usecase.GetReviewsUseCase
import com.luteh.core.domain.usecase.GetReviewsUseCaseParams

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class ReviewViewModel @ViewModelInject constructor(private val getReviewsUseCase: GetReviewsUseCase) : BaseViewModel() {

    var currentPage = 1
    var movieId = -1
    var isLastItemReviews = false

    private val reviewsPage = MutableLiveData<Int>()
    val reviews: LiveData<Resource<Reviews>>

    init {
        reviews = reviewsPage.switchMap {
            launchOnViewModelScope { getReviewsUseCase(GetReviewsUseCaseParams(movieId, it)).asLiveData() }
        }
    }

    fun getReviews() {
        reviewsPage.value = currentPage
        currentPage++
    }
}