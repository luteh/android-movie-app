package com.luteh.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.BuildConfig
import com.luteh.core.common.extensions.setImageFromUrlWithProgressBar
import com.luteh.detail.databinding.ItemDetailHeaderBinding
import com.luteh.core.domain.model.moviedetail.BackdropPoster

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