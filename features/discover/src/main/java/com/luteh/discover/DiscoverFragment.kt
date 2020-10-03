package com.luteh.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.luteh.core.R
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.extensions.gone
import com.luteh.core.common.extensions.shouldVisible
import com.luteh.core.common.utils.EndlessScrollListener
import com.luteh.core.data.Resource
import com.luteh.discover.adapter.DiscoverAdapter
import com.luteh.discover.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh on 9/6/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@AndroidEntryPoint
class DiscoverFragment : BaseFragment() {
    private val args: DiscoverFragmentArgs by navArgs()
    private val vm: DiscoverViewModel by viewModels()

    private lateinit var binding: FragmentDiscoverBinding

    private val adapter = DiscoverAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInit(savedInstanceState: Bundle?) {
        initActionBar(binding.toolbar.toolbarCommon, true, getString(R.string.title_discover))
        initRecyclerView()

        if (vm.currentPage == 1) {
            initViewModel()
        } else {
            binding.vLoading.pbCommon.gone()
        }

        initListener()
    }

    private fun initRecyclerView() {
        binding.rvDiscover.apply {
            adapter = this@DiscoverFragment.adapter
            addOnScrollListener(
                EndlessScrollListener(
                    isLoading = { binding.vLoading.pbCommon.isVisible },
                    loadMore = { getMovies() },
                    onLast = { vm.isLastItemMovies }
                )
            )
        }
    }

    private fun initViewModel() {
        vm.withGenres = args.genreId.toString()

        vm.discoverMovies.observe(this, { it ->
            Timber.d("$it")

            with(binding) {
                vLoading.pbCommon.shouldVisible(it is Resource.Loading)
                vLoading.tvRetry.shouldVisible(it is Resource.Error)

                when (it) {
                    is Resource.Loading -> {
                        Timber.d("Loading")
                    }
                    is Resource.Success -> {
                        Timber.d("Success ${it.data}")
                        it.data?.let {
                            adapter.setDataSource(it.results)
                            vm.isLastItemMovies = vm.currentPage > it.totalPages
                        }
                        com.luteh.core.common.utils.EspressoIdlingResource.decrement()
                    }
                    is Resource.Error -> {
                        Timber.e("Error ${it.message}")
                    }
                }
            }
        })

        getMovies()
    }

    private fun initListener() {
        binding.vLoading.tvRetry.setOnClickListener {
            getMovies(true)
        }
    }

    private fun getMovies(isRetry: Boolean = false) {
        if (isRetry) vm.currentPage--

        com.luteh.core.common.utils.EspressoIdlingResource.increment()
        vm.getMovies()
    }
}