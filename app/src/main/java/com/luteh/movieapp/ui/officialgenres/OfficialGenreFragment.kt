package com.luteh.movieapp.ui.officialgenres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.extensions.observe
import com.luteh.core.common.extensions.shouldVisible
import com.luteh.core.common.utils.EspressoIdlingResource
import com.luteh.core.data.Resource
import com.luteh.movieapp.databinding.FragmentOfficialGenreBinding
import com.luteh.movieapp.ui.officialgenres.adapter.OfficialGenreAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@AndroidEntryPoint
class OfficialGenreFragment : BaseFragment() {

    private val vm: OfficialGenreViewModel by viewModels()

    private lateinit var binding: FragmentOfficialGenreBinding
    private val adapter = OfficialGenreAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOfficialGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

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
            binding.vLoading.pbCommon.shouldVisible(it is Resource.Loading)
            binding.vLoading.tvRetry.shouldVisible(it is Resource.Error)

            when (it) {
                is Resource.Success -> {
                    adapter.setDataSource(it.data)
                    vm.isDataFetched = true
                    EspressoIdlingResource.decrement()
                }
                is Resource.Empty -> {

                }
                is Resource.Loading -> {

                }
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