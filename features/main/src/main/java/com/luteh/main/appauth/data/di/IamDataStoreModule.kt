package com.luteh.main.appauth.data.di

import com.luteh.main.appauth.data.local.datastore.IamDataStore
import com.luteh.main.appauth.data.local.datastore.IamDataStoreImpl
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