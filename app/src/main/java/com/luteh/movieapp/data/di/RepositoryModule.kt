package com.luteh.movieapp.data.di

import com.luteh.movieapp.data.MovieRepository
import com.luteh.movieapp.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@Module(includes = [NetworkModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository
}