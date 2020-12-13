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
import com.luteh.main.MainFragmentDirections
import com.luteh.main.databinding.FragmentHomeBinding
import com.luteh.main.home.adapter.HomeAdapter
import com.luteh.main.home.adapter.HomeItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

interface HomeItemCallback {
    fun navigateToDetailScreen(movieId: Int)
    fun reloadItemData(homeItem: HomeItem)
}

@AndroidEntryPoint
class HomeFragment : BaseFragment(), HomeItemCallback {

    private val vm: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = HomeAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInit(savedInstanceState: Bundle?) {
        initRecyclerView()
        initViewModel()
        initListener()
    }

    private fun initRecyclerView() {
        binding.rv.apply {
            adapter = this@HomeFragment.adapter
        }
    }

    private fun initViewModel() {
        // Header Banner
        observe(vm.moviesNowPlayingLiveData) {
            adapter.setDataSources(HomeItem.NOW_PLAYING, it)
            Timber.d("$it")
        }

        observe(vm.moviesPopularLiveData) {
            adapter.setDataSources(HomeItem.POPULAR, it)
            Timber.d("$it")
        }

        observe(vm.moviesTopRatedLiveData) {
            adapter.setDataSources(HomeItem.TOP_RATED, it)
            Timber.d("$it")
        }

        vm.onInitViewModel()
    }

    private fun initListener() {
    }

    override fun navigateToDetailScreen(movieId: Int) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(movieId)
        (parentFragment as NavHostFragment).parentFragment?.findNavController()?.navigate(action)
    }

    override fun reloadItemData(homeItem: HomeItem) {
        vm.reloadItemData(homeItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}