package com.luteh.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.luteh.core.common.base.BaseFragment
import com.luteh.main.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInit(savedInstanceState: Bundle?) {
        initActionBar(binding.toolbar.toolbarCommon, false)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_btm_nav) as NavHostFragment
        NavigationUI.setupWithNavController(binding.btmnav, navHostFragment.navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}