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
import com.luteh.core.common.delegates.viewBinding
import com.luteh.core.common.extensions.gone
import com.luteh.core.common.extensions.shouldVisible
import com.luteh.core.common.utils.EndlessScrollListener
import com.luteh.discover.adapter.DiscoverAdapter
import com.luteh.discover.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import com.luteh.core.data.Result

/**
 * Created by Luthfan Maftuh on 9/6/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@AndroidEntryPoint
class DiscoverFragment : BaseFragment(com.luteh.discover.R.layout.fragment_discover) {
    private val args: DiscoverFragmentArgs by navArgs()
    private val vm: DiscoverViewModel by viewModels()
    private val binding: FragmentDiscoverBinding by viewBinding()

    private val adapter = DiscoverAdapter()

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
                vLoading.pbCommon.shouldVisible(it is Result.Loading)
                vLoading.tvRetry.shouldVisible(it is Result.Error)

                when (it) {
                    is Result.Success -> {
                        Timber.d("Success ${it.data}")
                        it.data.let {
                            adapter.setDataSource(it.results)
                            vm.isLastItemMovies = vm.currentPage > it.totalPages
                        }
                        com.luteh.core.common.utils.EspressoIdlingResource.decrement()
                    }
                    is Result.Error -> {
                        Timber.d("Error ${it.throwable}")
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