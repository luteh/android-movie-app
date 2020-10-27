package com.luteh.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.core.common.base.BaseAdapter
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.databinding.ItemHomeHeaderBinding

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class HeaderAdapter : BaseAdapter<HeaderHolder, MovieDiscover>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderHolder {
        return HeaderHolder(ItemHomeHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HeaderHolder, position: Int) {
        holder.bindTo(dataSources[position])
    }
}