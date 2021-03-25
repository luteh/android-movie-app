package com.luteh.iam.data.di

import com.luteh.iam.data.repository.IamRepositoryImpl
import com.luteh.iam.domain.repository.IamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module(includes = [IamNetworkModule::class])
@InstallIn(ActivityComponent::class)
abstract class IamRepositoryModule {
    @Binds
    abstract fun provideIamRepository(iamRepositoryImpl: IamRepositoryImpl): IamRepository
}