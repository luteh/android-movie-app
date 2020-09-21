package com.luteh.movieapp.ui.trailer

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.luteh.movieapp.BuildConfig
import com.luteh.movieapp.R
import com.luteh.movieapp.common.extensions.gone
import com.luteh.movieapp.common.extensions.shouldVisible
import com.luteh.movieapp.databinding.ActivityTrailerBinding
import com.luteh.movieapp.domain.model.moviedetail.VideoResult
import timber.log.Timber

class TrailerActivity : YouTubeBaseActivity() {

    companion object {
        const val ARG_TRAILER_VIDEOS = "ARG_TRAILER_VIDEOS"
    }

    private lateinit var binding: ActivityTrailerBinding
    private var trailerVideos: List<VideoResult>? = null
    private val adapter = TrailerAdapter { videoResult, position ->
        onClickTrailerItem(videoResult, position)
    }

    private lateinit var youtubePlayer: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onInit()
    }

    private fun onInit() {
        trailerVideos = intent.extras?.getParcelableArrayList(ARG_TRAILER_VIDEOS)

        initActionBar()
        initView()
        initYoutube()
        initRecyclerView()
    }

    private fun initActionBar() {
        with(binding.toolbar) {
            toolbarCommon.apply {
                title = getString(R.string.label_watch_trailer)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    private fun initView() {
        with(binding) {
            vLoading.pbCommon.gone()
            vLoading.ivError.shouldVisible(trailerVideos.isNullOrEmpty())
        }
    }

    private fun initYoutube() {
        binding.youtubePlayer.initialize(BuildConfig.GOOGLE_API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider, p1: YouTubePlayer, p2: Boolean) {
                if (!::youtubePlayer.isInitialized)
                    youtubePlayer = p1

                trailerVideos?.let {
                    if (it.isNotEmpty())
                        youtubePlayer.cueVideo(it[0].key)
                }
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Timber.e("Youtube initialization failure $p1")
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvThumbnail.apply {
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            adapter = this@TrailerActivity.adapter
        }
        adapter.setDataSource(trailerVideos)
    }

    private fun onClickTrailerItem(videoResult: VideoResult, position: Int) {
        videoResult.let {
            youtubePlayer.cueVideo(it.key)
            adapter.setSelectedPosition(position)
        }
    }
}