package com.luteh.iam.data.di

import com.luteh.iam.data.local.datastore.IamDataStore
import com.luteh.iam.data.local.datastore.IamDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class IamDataStoreModule {
    @Binds
    abstract fun bindIamDataStore(iamDataStoreImpl: IamDataStoreImpl): IamDataStore
}