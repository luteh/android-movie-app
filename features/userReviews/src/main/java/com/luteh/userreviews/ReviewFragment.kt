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
import com.luteh.core.R
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.core.common.extensions.gone
import com.luteh.core.common.extensions.observe
import com.luteh.core.common.extensions.shouldVisible
import com.luteh.core.common.utils.EndlessScrollListener
import com.luteh.core.common.utils.EspressoIdlingResource
import com.luteh.core.data.Result
import com.luteh.userreviews.databinding.FragmentReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@AndroidEntryPoint
class ReviewFragment : BaseFragment(com.luteh.userreviews.R.layout.fragment_review) {

    private val args: ReviewFragmentArgs by navArgs()
    private val vm: ReviewViewModel by viewModels()
    private val binding: FragmentReviewBinding by viewBinding()
    private val adapter = ReviewAdapter()

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
            binding.loading.pbCommon.shouldVisible(it is Result.Loading)
            binding.loading.tvRetry.shouldVisible(it is Result.Error)
            binding.loading.ivError.shouldVisible(it is Result.Empty)

            when (it) {
                is Result.Loading -> {
                }
                is Result.Success -> {
                    it.data.let { data ->
                        binding.tvTotalReviews.text =
                            getString(R.string.label_total_review, data.totalResults)
                        adapter.addDataSource(data.reviewResults)
                        EspressoIdlingResource.decrement()

                        vm.isLastItemReviews = vm.currentPage > data.totalPages
                    }
                }
                is Result.Empty -> {
                    binding.tvTotalReviews.text = getString(R.string.label_total_review, 0)
                }
                is Result.Error -> {
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