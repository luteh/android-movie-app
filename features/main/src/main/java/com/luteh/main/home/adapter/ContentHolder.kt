package com.luteh.main.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.BuildConfig
import com.luteh.core.common.extensions.setImageFromUrlWithProgressBar
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.databinding.ItemHomeContentBinding
import com.luteh.main.home.HomeItemCallback

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class ContentHolder(
    private val binding: ItemHomeContentBinding,
    private val callback: HomeItemCallback
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(movieDiscover: MovieDiscover) {
        with(binding) {
            movieDiscover.let {
                ivPoster.setImageFromUrlWithProgressBar(
                    "${BuildConfig.BASE_URL_IMAGE_POSTER}${it.posterPath}",
                    vLoadingPoster.pbCommon,
                    vLoadingPoster.ivError
                )

                tvTitle.text = it.title
                tvScore.text = it.voteAverage.toString()

                itemView.setOnClickListener { _ ->
                    callback.navigateToDetailScreen(it.id)
                }
            }
        }
    }
}