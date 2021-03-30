package com.luteh.main.appauth.presentation.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.main.R
import com.luteh.main.appauth.appauthhelper.AppAuthHandler
import com.luteh.main.appauth.appauthhelper.AppAuthHandler.Companion.END_SESSION_REQUEST_CODE
import com.luteh.main.appauth.presentation.AuthActivity
import com.luteh.main.databinding.FragmentIamAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class IamAccountFragment : BaseFragment(R.layout.fragment_iam_account) {

    private val binding by viewBinding<FragmentIamAccountBinding>()
    private val vm by viewModels<IamAccountViewModel>()

    private val appAuthHandler by lazy {
        AppAuthHandler.getInstance(
            requireActivity(),
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appAuthHandler.initAppAuth()
    }

    override fun onInit(savedInstanceState: Bundle?) {
        initView()
        initListener()
    }

    private fun initView() {

    }

    private fun initListener() {
        with(binding) {
            btnLogInOut.setOnClickListener {
                when {
                    appAuthHandler.mIsAuthorized -> {
                        appAuthHandler.endSession()
                    }
                    else -> {
// TODO: 30/03/2021 Do login
                    }
                }
            }
        }
    }

    private fun navigateToAuthScreen() {
        startActivity(Intent(requireContext(), AuthActivity::class.java))
        requireActivity().finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.d("onActivityResult: requestCode-> $requestCode")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == END_SESSION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            appAuthHandler.signOut()
            requireActivity().finish()
        } else {
//            displayEndSessionCancelled()
        }
    }


    override fun onResume() {
        super.onResume()
        when (appAuthHandler.mIsAuthorized) {
            true -> {
                setupAuthorizedView()
            }
            else -> {
                setupUnauthorizedView()
            }
        }
    }

    private fun setupUnauthorizedView() {
        Timber.d("setupUnauthorizedView: started")

        with(binding) {
            btnLogInOut.text = getString(R.string.label_login)
        }
    }

    private fun setupAuthorizedView() {
        Timber.d("setupAuthorizedView: started")

        with(binding) {
            btnLogInOut.text = getString(R.string.label_logout)
        }
    }

    override fun onStart() {
        super.onStart()
        appAuthHandler.onStart()
    }

    override fun onDestroy() {
        appAuthHandler.onDestroy()
        super.onDestroy()
    }
}