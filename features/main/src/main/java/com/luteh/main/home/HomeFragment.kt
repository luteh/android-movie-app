package com.luteh.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.extensions.observe
import com.luteh.main.R
import com.luteh.main.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val vm: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInit(savedInstanceState: Bundle?) {
        initListener()
        initViewModel()
    }

    private fun initViewModel() {
        observe(vm.moviesNowPlayingLiveData) {
            Timber.d("$it")
        }

        vm.getMoviesByNowPlaying()
    }

    private fun initListener() {
        binding.tv.setOnClickListener {
            (parentFragment as NavHostFragment).parentFragment?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_officialGenreFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}