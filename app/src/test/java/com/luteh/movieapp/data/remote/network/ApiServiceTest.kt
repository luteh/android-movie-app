package com.luteh.movieapp.data.remote.network

import com.luteh.movieapp.common.utils.MockWebServerBaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class ApiServiceTest : MockWebServerBaseTest() {

    private lateinit var apiService: ApiService

    override fun isMockServerEnabled(): Boolean = true

    @Before
    fun start() {
        apiService = provideTestApiService()
    }

    @Test
    fun testGetMovieDiscover() = runBlocking {
        mockHttpResponse("movie_discover.json", HttpURLConnection.HTTP_OK)
        val apiResponse = apiService.getMovieDiscover(1, "28")

        assertNotNull(apiResponse)
        assertNotNull(apiResponse!!.results)
        assertEquals(apiResponse.totalResults, 10000)
        assertEquals(apiResponse.results[0].title, "Project Power")
    }

    @Test
    fun testGetOfficialGenres() = runBlocking {
        mockHttpResponse("official_genres.json", HttpURLConnection.HTTP_OK)
        val apiResponse = apiService.getOfficialGenres()

        assertNotNull(apiResponse)
        assertNotNull(apiResponse.genres)
        assertEquals(apiResponse.genres.size, 19)
        assertEquals(apiResponse.genres[0].id, 28)
    }

    @Test
    fun testGetMovieDetail() = runBlocking {
        mockHttpResponse("movie_detail.json", HttpURLConnection.HTTP_OK)
        val apiResponse = apiService.getMovieDetail(337401)

        assertNotNull(apiResponse)
        assertEquals(apiResponse!!.title, "Mulan")
    }

    @Test
    fun testGetReviews() = runBlocking {
        mockHttpResponse("reviews.json", HttpURLConnection.HTTP_OK)
        val apiResponse = apiService.getReviews(337401, 1)

        assertNotNull(apiResponse)
        assertEquals(apiResponse!!.results.size, 5)
        assertEquals(apiResponse.results[0].author, "tricksy")
    }
}