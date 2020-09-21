package com.luteh.movieapp.ui.main

import android.os.Bundle
import com.luteh.movieapp.databinding.ActivityMainBinding
import com.luteh.movieapp.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onInit() {
    }
}