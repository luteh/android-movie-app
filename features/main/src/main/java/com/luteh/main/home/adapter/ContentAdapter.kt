package com.luteh.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.core.common.base.BaseAdapter
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.databinding.ItemHomeContentBinding
import com.luteh.main.home.HomeItemCallback

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class ContentAdapter(private val callback: HomeItemCallback) : BaseAdapter<ContentHolder, MovieDiscover>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {
        return ContentHolder(
            ItemHomeContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callback
        )
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bindTo(dataSources[position])
    }
}