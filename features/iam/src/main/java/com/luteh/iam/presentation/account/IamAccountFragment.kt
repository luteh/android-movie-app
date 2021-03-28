package com.luteh.iam.presentation.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.core.data.Result
import com.luteh.iam.R
import com.luteh.iam.databinding.FragmentIamAccountBinding
import com.luteh.iam.domain.model.KeycloakToken
import com.luteh.iam.presentation.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class IamAccountFragment : BaseFragment(R.layout.fragment_iam_account) {

    private val binding by viewBinding<FragmentIamAccountBinding>()
    private val vm by viewModels<IamAccountViewModel>()

    override fun onInit(savedInstanceState: Bundle?) {
        initView()
        initListener()

        observeLiveData()

        vm.retrieveUserToken()
    }

    private fun observeLiveData() {
        observeLogOutProcessState()
        observeUserTokenLiveData()
    }

    private fun observeUserTokenLiveData() {
        vm.userTokenLiveData.observe(this, {
            Timber.d("observeUserTokenLiveData: $it")
            when (it) {
                is Result.Success -> {
                    onSuccessRetrieveUserToken(it.data)
                }
                is Result.Empty -> {
                    onEmptyRetrieveUserToken()
                }
                else -> {

                }
            }
        })
    }

    private fun onEmptyRetrieveUserToken() {
        binding.btnLogInOut.text = getString(R.string.label_login)
    }

    private fun onSuccessRetrieveUserToken(data: KeycloakToken) {
        binding.btnLogInOut.text = getString(R.string.label_logout)
    }

    private fun observeLogOutProcessState() {
        vm.logOutProcessState.observe(this, {
            Timber.d("logOutProcessState: $it")
            when (it) {
                is Result.Success -> {
                    Timber.d("logOutProcessState: Success-> ${it.data}")
                }
                is Result.Error -> {
                    Timber.d("logOutProcessState: Error-> ${it.throwable.message}")
                }
                else -> {

                }
            }
        })
    }

    private fun initView() {

    }

    private fun initListener() {
        with(binding) {
            btnLogInOut.setOnClickListener {
                Timber.d("initListener: userTokenLiveData-> ${vm.userTokenLiveData.value}")
                when (vm.userTokenLiveData.value) {
                    is Result.Success -> {
                        vm.logOut()
                    }
                    is Result.Empty -> {
                        navigateToAuthScreen()
                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun navigateToAuthScreen() {
        startActivity(Intent(requireContext(), AuthActivity::class.java))
        requireActivity().finish()
    }
}