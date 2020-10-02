package com.luteh.movieapp.ui.discover.adapter

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.BuildConfig
import com.luteh.core.common.extensions.setImageFromUrlWithProgressBar
import com.luteh.core.common.utils.DateUtils
import com.luteh.movieapp.databinding.ItemDiscoverBinding
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.movieapp.ui.discover.DiscoverFragmentDirections

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class DiscoverHolder(private val binding: ItemDiscoverBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movieDiscover: MovieDiscover) {
        with(binding) {
            movieDiscover.let {
                ivItemDiscover.setImageFromUrlWithProgressBar(BuildConfig.BASE_URL_IMAGE_POSTER + it.posterPath, vLoadingPoster.containerLoading, vLoadingPoster.ivError)

                tvTitle.text = it.title
                tvReleaseDate.text = DateUtils.changeStrDateFormat(it.releaseDate, DateUtils.DATE_FORMAT_SHORT_US, DateUtils.DATE_FORMAT_YEAR)
                tvScore.text = it.voteAverage.toString()

                itemView.setOnClickListener { v ->
                    v.findNavController().navigate(DiscoverFragmentDirections.actionDiscoverFragmentToDetailFragment(it.id))
                }
            }
        }
    }
}