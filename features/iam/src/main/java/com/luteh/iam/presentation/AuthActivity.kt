package com.luteh.iam.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luteh.core.common.delegates.viewBinding
import com.luteh.iam.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityAuthBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onInit()
    }

    private fun onInit() {
        with(binding) {

        }
    }
}



