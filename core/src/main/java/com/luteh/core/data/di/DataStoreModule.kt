package com.luteh.core.data.di

import com.luteh.core.data.local.datastore.MovieDataStore
import com.luteh.core.data.local.datastore.MovieDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataStoreModule {
    @Binds
    abstract fun bindMovieDataStore(movieDataStoreImpl: MovieDataStoreImpl): MovieDataStore
}