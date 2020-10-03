package com.luteh.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.luteh.core.BuildConfig
import com.luteh.core.R
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.constants.ActionConstants
import com.luteh.core.common.extensions.*
import com.luteh.core.common.utils.EspressoIdlingResource
import com.luteh.core.data.Resource
import com.luteh.core.domain.model.moviedetail.MovieDetail
import com.luteh.detail.adapter.DetailGenreAdapter
import com.luteh.detail.adapter.DetailHeaderAdapter
import com.luteh.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by Luthfan Maftuh on 9/6/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private val args: DetailFragmentArgs by navArgs()
    private val vm: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding

    private val genreAdapter = DetailGenreAdapter()
    private val headerAdapter = DetailHeaderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInit(savedInstanceState: Bundle?) {
        initActionBar(binding.toolbar.toolbarCollapsing, true, "")
        initRecyclerView()
        initViewModel()
        initListener()
    }

    private fun initRecyclerView() {
        with(binding) {
            rvGenre.apply {
                adapter = genreAdapter
            }

            toolbar.vpHeader.apply {
                adapter = headerAdapter
            }
        }
    }

    private fun initViewModel() {
        observe(vm.movieDetailLiveData) {
            binding.vLoading.pbCommon.shouldVisible(it is Resource.Loading)
            binding.vLoading.tvRetry.shouldVisible(it is Resource.Error)

            when (it) {
                is Resource.Success -> {
                    bind(it.data)
                    vm.isDataFetched = true
                    EspressoIdlingResource.decrement()
                }
                is Resource.Error -> {
                    Timber.e(it.throwable)
                }
                is Resource.Loading -> {
                }
            }
        }

        getMovieDetail()
    }

    private fun initListener() {
        binding.vLoading.tvRetry.setOnClickListener {
            getMovieDetail()
        }
    }

    private fun getMovieDetail() {
        if (!vm.isDataFetched) {
            EspressoIdlingResource.increment()
            vm.getMovieDetail(args.movieId)
        } else {
            binding.vLoading.pbCommon.gone()
        }
    }

    private fun bind(data: MovieDetail?) {
        data?.let {
            setupView(it)
            setupListener(it)
        }
    }

    private fun setupView(movieDetail: MovieDetail) {
        with(binding) {
            toolbar.toolbarCollapsing.title = movieDetail.title

            ivPoster.setImageFromUrlWithProgressBar(
                BuildConfig.BASE_URL_IMAGE_POSTER + movieDetail.posterPath,
                vLoadingPoster.containerLoading,
                vLoadingPoster.ivError
            )

            tvRating.text = getString(
                R.string.value_vote_count,
                movieDetail.voteAverage.toString(),
                movieDetail.voteCount.toString()
            )
            tvDetailTitle.text = movieDetail.title
            tvDetailOverview.text = movieDetail.overview

            headerAdapter.setDataSource(movieDetail.images.backdrops)
            genreAdapter.setDataSource(movieDetail.genres)
        }
    }

    private fun setupListener(movieDetail: MovieDetail) {
        with(binding) {
            cvWatchTrailer.setOnClickListener { v ->
                requireContext().navigateTo(
                    Features.TRAILER,
                    bundleOf(ActionConstants.ARG_TRAILER_VIDEOS to movieDetail.videos.videoResults)
                )
            }

            cvSeeReview.setOnClickListener { _ ->
                val action =
                    DetailFragmentDirections.actionDetailFragmentToReviewFragment(movieDetail.id)
                findNavController().navigate(action)
            }
        }
    }
}