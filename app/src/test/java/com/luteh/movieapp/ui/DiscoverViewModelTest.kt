package com.luteh.movieapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luteh.movieapp.common.utils.MainCoroutinesRule
import com.luteh.movieapp.data.Resource
import com.luteh.movieapp.data.remote.RemoteDataSource
import com.luteh.movieapp.data.remote.network.ApiService
import com.luteh.movieapp.domain.model.Discover
import com.luteh.movieapp.domain.repository.IMovieRepository
import com.luteh.movieapp.domain.usecase.GetMovieDiscoverUseCase
import com.luteh.movieapp.ui.discover.DiscoverViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@ExperimentalCoroutinesApi
class DiscoverViewModelTest {

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var vm: DiscoverViewModel
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var getMovieDiscoverUseCase: GetMovieDiscoverUseCase

    private val apiService: ApiService = mock()
    private val repository: IMovieRepository = mock()
    private val observer: Observer<Resource<Discover>> = mock()

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSource(apiService)
        getMovieDiscoverUseCase = GetMovieDiscoverUseCase(repository, Dispatchers.IO)
        vm = DiscoverViewModel(getMovieDiscoverUseCase)
    }

    @Test
    fun testGetMoviesDiscover() = runBlocking {
        // Given
        vm.withGenres = "28"
        val mockData = flow { emit(Resource.Success(Discover(0, 0, 0, emptyList()))) }
        whenever(repository.getMovieDiscover(1, vm.withGenres)).thenReturn(mockData)

        // When
        vm.discoverMovies.observeForever(observer)
        vm.getMovies()

        // Then
        assertNotNull(vm.discoverMovies)
        assertTrue(vm.discoverMovies.hasObservers())
        verify(repository, atLeastOnce()).getMovieDiscover(1, vm.withGenres)
        vm.discoverMovies.removeObserver(observer)
    }
}