package com.luteh.userreviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.extensions.gone
import com.luteh.core.common.extensions.observe
import com.luteh.core.common.extensions.shouldVisible
import com.luteh.core.common.utils.EndlessScrollListener
import com.luteh.core.common.utils.EspressoIdlingResource
import com.luteh.core.data.Resource
import com.luteh.core.R
import com.luteh.userreviews.databinding.FragmentReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@AndroidEntryPoint
class ReviewFragment : BaseFragment() {

    private val args: ReviewFragmentArgs by navArgs()
    private val vm: ReviewViewModel by viewModels()
    private lateinit var binding: FragmentReviewBinding
    private val adapter = ReviewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInit(savedInstanceState: Bundle?) {
        initActionBar(binding.toolbar.toolbarCommon, true, getString(R.string.title_user_reviews))
        initRecyclerView()
        if (vm.movieId == -1) {
            initViewModel()
        } else {
            binding.loading.pbCommon.gone()
        }
        initListener()
    }

    private fun initRecyclerView() {
        binding.rvReview.apply {
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            addOnScrollListener(EndlessScrollListener(
                isLoading = { binding.loading.pbCommon.isVisible },
                loadMore = { vm.getReviews() },
                onLast = { vm.isLastItemReviews }
            ))
            adapter = this@ReviewFragment.adapter
        }
    }

    private fun initViewModel() {
        vm.movieId = args.movieId

        observe(vm.reviews) {
            binding.loading.pbCommon.shouldVisible(it is Resource.Loading)
            binding.loading.tvRetry.shouldVisible(it is Resource.Error)
            binding.loading.ivError.shouldVisible(it is Resource.Empty)

            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    it.data?.let { data ->
                        binding.tvTotalReviews.text = getString(R.string.label_total_review, data.totalResults)
                        adapter.addDataSource(data.reviewResults)
                        EspressoIdlingResource.decrement()

                        vm.isLastItemReviews = vm.currentPage > data.totalPages
                    }
                }
                is Resource.Empty -> {
                    binding.tvTotalReviews.text = getString(R.string.label_total_review, 0)
                }
                is Resource.Error -> {
                    Timber.e(it.throwable)
                }
            }
        }

        getReviews()
    }

    private fun initListener() {
        binding.loading.tvRetry.setOnClickListener {
            getReviews(true)
        }
    }

    private fun getReviews(isRetry: Boolean = false) {
        if (isRetry) vm.currentPage--

        EspressoIdlingResource.increment()
        vm.getReviews()
    }
}