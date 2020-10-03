package com.luteh.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.core.common.base.BaseAdapter
import com.luteh.detail.databinding.ItemDetailHeaderBinding
import com.luteh.core.domain.model.moviedetail.BackdropPoster

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class DetailHeaderAdapter : BaseAdapter<DetailHeaderHolder, BackdropPoster>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHeaderHolder =
        DetailHeaderHolder(ItemDetailHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DetailHeaderHolder, position: Int) {
        holder.bind(dataSources[position])
    }
}