package com.luteh.movieapp.ui.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.luteh.movieapp.BuildConfig
import com.luteh.movieapp.common.extensions.setImageFromUrlWithProgressBar
import com.luteh.movieapp.databinding.ItemDetailHeaderBinding
import com.luteh.movieapp.domain.model.moviedetail.BackdropPoster

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class DetailHeaderHolder(private val binding: ItemDetailHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(backdropPoster: BackdropPoster) {
        with(binding) {
            ivHeader.setImageFromUrlWithProgressBar(
                BuildConfig.BASE_URL_IMAGE_BACKDROP + backdropPoster.filePath,
                vLoading.pbCommon,
                vLoading.ivError
            )
        }
    }
}