package com.luteh.main.appauth.presentation.checkusername

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.main.R
import com.luteh.main.databinding.FragmentCheckUsernameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckUsernameFragment : BaseFragment(R.layout.fragment_check_username) {

    private val binding by viewBinding<FragmentCheckUsernameBinding>()

    private val fakeUsernameData = listOf("test1", "test2")

    override fun onInit(savedInstanceState: Bundle?) {
        with(binding) {
            btnCheck.setOnClickListener {
                val username = etUsername.text.toString()
                val isUsernameExisted = fakeUsernameData.contains(username)

                showToastUsernameState(isUsernameExisted)
                navigateToKeycloakScreen(username, isUsernameExisted)
            }
        }
    }

    private fun showToastUsernameState(isUsernameExisted: Boolean) {
        Toast.makeText(
            requireContext(),
            if (isUsernameExisted) "Username exist!" else "Username not exist!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun navigateToKeycloakScreen(username: String, isUsernameExisted: Boolean) {
        val action =
            CheckUsernameFragmentDirections.actionCheckUsernameFragmentToKeycloakFragment(
                username,
                isUsernameExisted
            )
        findNavController().navigate(action)
    }
}