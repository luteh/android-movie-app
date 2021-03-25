package com.luteh.iam.data.di

import com.luteh.iam.data.remote.network.IamApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class KeycloakOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object IamNetworkModule {
    private const val IAM_LOCAL_BASE_URL = "http://10.0.2.2:8080/auth/"

    @KeycloakOkHttpClient
    @Provides
    fun provideKeycloakOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideKeycloakApiService(@KeycloakOkHttpClient client: OkHttpClient): IamApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(IAM_LOCAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(IamApiService::class.java)
    }
}