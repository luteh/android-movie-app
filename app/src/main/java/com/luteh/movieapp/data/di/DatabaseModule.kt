package com.luteh.movieapp.data.di

import android.content.Context
import androidx.room.Room
import com.luteh.movieapp.data.local.room.MovieDao
import com.luteh.movieapp.data.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, "Movie.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()
}