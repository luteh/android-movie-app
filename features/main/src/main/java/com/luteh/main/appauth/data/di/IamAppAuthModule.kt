package com.luteh.main.appauth.data.di

import android.content.Context
import com.luteh.main.appauth.data.appauth.AuthStateManager
import com.luteh.main.appauth.data.appauth.Configuration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class IamAppAuthModule {
    @Provides
    fun provideAppAuthManager(@ApplicationContext context: Context) =
        AuthStateManager.getInstance(context)

    @Provides
    fun provideConfiguration(@ApplicationContext context: Context) =
        Configuration.getInstance(context)
}