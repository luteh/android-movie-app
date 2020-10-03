package com.luteh.genres

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luteh.core.data.Resource
import com.luteh.core.domain.model.moviedetail.Genre
import com.luteh.core.domain.usecase.GetGenresUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class OfficialGenreViewModel @ViewModelInject constructor(
    private val getGenresUseCase: GetGenresUseCase
) : com.luteh.core.common.base.BaseViewModel() {

    val genresLiveData = MutableLiveData<Resource<List<Genre>>>()
    var isDataFetched = false

    fun getOfficialGenres() {
        viewModelScope.launch {
            getGenresUseCase(Unit).collect {
                genresLiveData.value = it
            }
        }
    }
}