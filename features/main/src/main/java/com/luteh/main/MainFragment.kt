package com.luteh.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.main.databinding.FragmentMainBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    override fun onInit(savedInstanceState: Bundle?) {
        initActionBar(binding.toolbar.toolbarCommon, false)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_btm_nav) as NavHostFragment
        NavigationUI.setupWithNavController(binding.btmnav, navHostFragment.navController)
    }
}