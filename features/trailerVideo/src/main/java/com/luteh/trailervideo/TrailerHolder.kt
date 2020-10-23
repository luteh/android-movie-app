package com.luteh.trailervideo

import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.luteh.core.BuildConfig
import com.luteh.trailervideo.databinding.ItemTrailerBinding
import com.luteh.core.domain.model.moviedetail.VideoResult
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class TrailerHolder(private val binding: ItemTrailerBinding, private val callback: (VideoResult, Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(videoResult: VideoResult, selectedPosition: Int) {
        with(binding) {
            videoResult.let {

                tvName.text = it.name

                youtubeThumbnail.initialize(BuildConfig.GOOGLE_API_KEY, object : YouTubeThumbnailView.OnInitializedListener {
                    override fun onInitializationSuccess(p0: YouTubeThumbnailView, youTubeThumbnailLoader: YouTubeThumbnailLoader) {
                        youTubeThumbnailLoader.apply {
                            setVideo(it.key)

                            setOnThumbnailLoadedListener(object : YouTubeThumbnailLoader.OnThumbnailLoadedListener {
                                override fun onThumbnailLoaded(p0: YouTubeThumbnailView?, p1: String?) {
                                    youTubeThumbnailLoader.release()
                                }

                                override fun onThumbnailError(p0: YouTubeThumbnailView?, p1: YouTubeThumbnailLoader.ErrorReason?) {
                                    Timber.e("Youtube thumbnail error $p1")
                                }
                            })
                        }
                    }

                    override fun onInitializationFailure(p0: YouTubeThumbnailView?, p1: YouTubeInitializationResult?) {
                        Timber.e("Youtube initialization failure $p1")
                    }
                })

                container.isSelected = selectedPosition == adapterPosition

                itemView.setOnClickListener { _ ->
                    if (!container.isSelected) {
                        callback.invoke(it, adapterPosition)
                    }
                }
            }
        }
    }
}