package com.luteh.main

import android.os.Bundle
import com.luteh.core.common.base.BaseActivity
import com.luteh.core.common.delegates.viewBinding
import com.luteh.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {
//    private val binding: ActivityMainBinding by viewBinding()

    override fun onInit() {
    }
}