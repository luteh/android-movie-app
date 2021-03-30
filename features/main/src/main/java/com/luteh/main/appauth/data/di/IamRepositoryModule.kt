package com.luteh.main.appauth.data.di

import com.luteh.main.appauth.data.repository.IamRepositoryImpl
import com.luteh.main.appauth.domain.repository.IamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [IamNetworkModule::class, IamAppAuthModule::class])
@InstallIn(ApplicationComponent::class)
abstract class IamRepositoryModule {
    @Binds
    abstract fun provideIamRepository(iamRepositoryImpl: IamRepositoryImpl): IamRepository
}