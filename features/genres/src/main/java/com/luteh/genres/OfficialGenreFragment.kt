package com.luteh.genres

import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.core.common.extensions.observe
import com.luteh.core.common.extensions.shouldVisible
import com.luteh.core.common.utils.EspressoIdlingResource
import com.luteh.core.data.Result
import com.luteh.genres.adapter.OfficialGenreAdapter
import com.luteh.genres.databinding.FragmentOfficialGenreBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@AndroidEntryPoint
class OfficialGenreFragment : BaseFragment(R.layout.fragment_official_genre) {

    private val vm: OfficialGenreViewModel by viewModels()
    private val binding: FragmentOfficialGenreBinding by viewBinding()

    private val adapter = OfficialGenreAdapter()

    override fun onInit(savedInstanceState: Bundle?) {
        initActionBar(binding.toolbar.toolbarCommon, false)
        initRecyclerView()
        initViewModel()
        initListener()
    }

    private fun initRecyclerView() {
        binding.rvOfficialGenre.apply {
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            adapter = this@OfficialGenreFragment.adapter
        }
    }

    private fun initViewModel() {
        observe(vm.genresLiveData) {
            Timber.d("$it")
            binding.vLoading.pbCommon.shouldVisible(it is Result.Loading)
            binding.vLoading.tvRetry.shouldVisible(it is Result.Error)

            when (it) {
                is Result.Success -> {
                    adapter.setDataSource(it.data)
                    vm.isDataFetched = true
                    EspressoIdlingResource.decrement()
                }
                else ->{}
            }
        }

        getOfficialGenres()
    }

    private fun initListener() {
        binding.vLoading.tvRetry.setOnClickListener {
            getOfficialGenres()
        }
    }

    private fun getOfficialGenres() {
        if (vm.isDataFetched) return

        EspressoIdlingResource.increment()
        vm.getOfficialGenres()
    }
}