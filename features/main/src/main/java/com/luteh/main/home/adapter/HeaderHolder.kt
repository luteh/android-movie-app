package com.luteh.main.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.BuildConfig
import com.luteh.core.common.extensions.setImageFromUrlWithProgressBar
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.databinding.ItemHomeHeaderBinding
import com.luteh.main.home.HomeItemCallback

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class HeaderHolder(
    private val binding: ItemHomeHeaderBinding,
    private val callback: HomeItemCallback
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(movieDiscover: MovieDiscover) {
        with(binding) {
            movieDiscover.let {
                ivBackdrop.setImageFromUrlWithProgressBar(
                    "${BuildConfig.BASE_URL_IMAGE_BACKDROP}${it.backdropPath}",
                    vLoading.pbCommon,
                    vLoading.ivError
                )

                tvTitle.text = it.title

                itemView.setOnClickListener { _ ->
                    callback.navigateToDetailScreen(it.id)
                }
            }
        }
    }
}