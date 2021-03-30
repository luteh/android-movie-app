package com.luteh.main.appauth.presentation.splash

import android.content.Intent
import androidx.activity.viewModels
import com.luteh.core.common.base.BaseActivity
import com.luteh.core.common.extensions.Features
import com.luteh.core.common.extensions.navigateTo
import com.luteh.core.data.Result
import com.luteh.main.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : BaseActivity(R.layout.activity_splash) {

    private val vm by viewModels<SplashViewModel>()

    override fun onInit() {
        vm.initAppAuth()

        observeLiveData()
//        vm.determine()
        vm.checkAuthenticationState()
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

        vm.isAuthenticatedLiveData.observe(this, {
            Timber.d("observeLiveData: isAuthenticated-> $it")
            if (it) {
                navigateToMainScreen()
            } else {
                navigateToAuthScreen()
            }
        })
    }

    private fun navigateToMainScreen() {
        navigateTo(Features.MAIN)
        finish()
    }

    private fun navigateToAuthScreen() {
//        startActivity(Intent(this, AuthActivity::class.java))
//        finish()

        vm.authService?.let {
            Timber.d("navigateToAuthScreen: authService-> $it")
            val intent: Intent = it.getAuthorizationRequestIntent(
                vm.authRequest.get(),
                vm.authIntent.get()
            )
            startActivityForResult(intent, 100)
        }
    }
}