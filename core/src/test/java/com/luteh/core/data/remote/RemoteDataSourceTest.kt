package com.luteh.core.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luteh.core.utils.MainCoroutinesRule
import com.luteh.core.data.remote.network.ApiResponse
import com.luteh.core.data.remote.network.ApiService
import com.luteh.core.data.remote.response.discover.DiscoverResponse
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var remoteDataSource: RemoteDataSource

    private val apiService: ApiService = mock()

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun testGetMovieDiscover(): Unit = runBlocking {
        // Given
        val mockData = DiscoverResponse(0, 0, 0, emptyList())
        whenever(apiService.getMovieDiscover(1, "28")).thenReturn(mockData)

        // When
        remoteDataSource.getMovieDiscover(1, "28")
            .collect {
                // Then
                Assert.assertNotNull(it)
                Assert.assertEquals(it, ApiResponse.Empty)
            }

        verify(apiService, atLeastOnce()).getMovieDiscover(1, "28")
    }
}