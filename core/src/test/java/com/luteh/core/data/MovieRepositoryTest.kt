package com.luteh.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luteh.core.utils.MainCoroutinesRule
import com.luteh.core.data.local.LocalDataSource
import com.luteh.core.data.local.room.MovieDao
import com.luteh.core.data.remote.RemoteDataSource
import com.luteh.core.data.remote.network.ApiService
import com.luteh.core.data.remote.response.discover.DiscoverResponse
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

/**
 * Created by Luthfan Maftuh on 9/17/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@ExperimentalTime
@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: MovieRepository
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource

    private val apiService: ApiService = mock()
    private val movieDao: MovieDao = mock()

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSource(apiService)
        localDataSource = LocalDataSource(movieDao)
        repository = MovieRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun testGetMovieDiscover(): Unit = runBlocking {
        // Given
        val mockData = DiscoverResponse(0, 0, 0, emptyList())
        whenever(apiService.getMovieDiscover(1, "28")).thenReturn(mockData)

        // When
        repository.getMovieDiscover(1, "28")
            .filter {
                it !is Resource.Loading
            }
            // Then
            .collect {
                assertNotNull(it)
                assertEquals(it, Resource.Empty)
            }

        verify(apiService, atLeastOnce()).getMovieDiscover(1, "28")
    }
}