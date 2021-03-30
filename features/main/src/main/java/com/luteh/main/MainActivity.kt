package com.luteh.main

import android.content.Intent
import android.os.Bundle
import com.luteh.core.common.base.BaseActivity
import com.luteh.core.common.delegates.viewBinding
import com.luteh.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {
//    private val binding: ActivityMainBinding by viewBinding()

    override fun onInit() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("onActivityResult: requestCode-> $requestCode")
    }
}