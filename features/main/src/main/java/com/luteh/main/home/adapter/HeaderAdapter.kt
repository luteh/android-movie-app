package com.luteh.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.core.common.base.BaseAdapter
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.databinding.ItemHomeHeaderBinding
import com.luteh.main.home.HomeItemCallback

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class HeaderAdapter(private val callback: HomeItemCallback) :
    BaseAdapter<HeaderHolder, MovieDiscover>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderHolder {
        return HeaderHolder(
            ItemHomeHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), callback
        )
    }

    override fun onBindViewHolder(holder: HeaderHolder, position: Int) {
        val index = position % dataSources.size
        holder.bindTo(dataSources[index])
    }

    override fun getItemCount(): Int = if (dataSources.isNotEmpty()) Integer.MAX_VALUE else 0
}