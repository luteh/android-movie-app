package com.luteh.main.account

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.core.common.extensions.observe
import com.luteh.core.data.Result
import com.luteh.main.R
import com.luteh.main.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

@AndroidEntryPoint
class AccountFragment : BaseFragment(R.layout.fragment_account) {

    private val binding: FragmentAccountBinding by viewBinding()
    private val vm: AccountViewModel by viewModels()

    override fun onInit(savedInstanceState: Bundle?) {
        initListener()
        observeIsLoggedIn()
    }

    private fun initListener() {
        with(binding) {
            btnLogInOut.setOnClickListener {
                vm.doLogInOrOut()
            }
        }
    }

    private fun observeIsLoggedIn() {
        observe(vm.isLoggedIn) {
            Timber.d("observeIsLoggedIn: $it")

            when (it) {
                is Result.Success -> {
                    onSuccessGetLoggedInStatus(it.data)
                }
                else              -> {
                    onGetLoggedInStatusReturnElse()
                }
            }
        }
    }

    private fun onGetLoggedInStatusReturnElse() {
        Timber.d("onGetLoggedInStatusReturnElse: ")
    }

    private fun onSuccessGetLoggedInStatus(data: Boolean) {
        Timber.d("onSuccessGetLoggedInStatus: $data")
        with(binding) {
            if (data) {
                btnLogInOut.text = getString(R.string.label_logout)
            } else {
                btnLogInOut.text = getString(R.string.label_login)
            }

        }
    }
}