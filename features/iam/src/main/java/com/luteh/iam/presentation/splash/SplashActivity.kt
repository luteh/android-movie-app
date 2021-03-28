package com.luteh.iam.presentation.splash

import android.content.Intent
import androidx.activity.viewModels
import com.luteh.core.common.base.BaseActivity
import com.luteh.core.common.extensions.Features
import com.luteh.core.common.extensions.navigateTo
import com.luteh.core.data.Result
import com.luteh.iam.R
import com.luteh.iam.presentation.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : BaseActivity(R.layout.activity_splash) {

    private val vm by viewModels<SplashViewModel>()

    override fun onInit() {
        observeLiveData()
        vm.determine()
    }

    private fun observeLiveData() {
        vm.keycloakTokenLiveData.observe(this, {
            Timber.d("observeLiveData: $it")

            when (it) {
                is Result.Success -> {
                    navigateToMainScreen()
                }
                is Result.Empty -> {
                    navigateToAuthScreen()
                }
                else -> {

                }
            }
        })
    }

    private fun navigateToMainScreen() {
        navigateTo(Features.MAIN)
        finish()
    }

    private fun navigateToAuthScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}