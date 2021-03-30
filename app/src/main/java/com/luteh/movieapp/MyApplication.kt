package com.luteh.movieapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * Created by Luthfan Maftuh on 8/26/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@HiltAndroidApp
open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
//        }
    }
}