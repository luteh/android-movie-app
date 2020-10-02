package com.luteh.core.common.utils

import androidx.test.espresso.idling.CountingIdlingResource


/**
 * Created by Luthfan Maftuh
 * Email luthfanmaftuh@gmail.com
 */
object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val espressoTestIdlingResource by lazy { CountingIdlingResource(RESOURCE) }

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        if (!espressoTestIdlingResource.isIdleNow) {
            espressoTestIdlingResource.decrement()
        }
    }
}