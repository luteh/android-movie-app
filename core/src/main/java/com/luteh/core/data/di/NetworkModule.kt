package com.luteh.core.data.di

import com.luteh.core.BuildConfig
import com.luteh.core.data.remote.network.ApiService
import com.luteh.core.di.KeycloakApiService
import com.luteh.core.di.KeycloakOkHttpClient
import com.luteh.core.di.MovieApiService
import com.luteh.core.di.MovieOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @MovieOkHttpClient
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.MOVIEDB_API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(requestInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @KeycloakOkHttpClient
    @Provides
    fun provideKeycloakOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @MovieApiService
    @Provides
    fun provideApiService(@MovieOkHttpClient client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_MOVIEDB)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @KeycloakApiService
    @Provides
    fun provideKeycloakApiService(@KeycloakOkHttpClient client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/auth/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}